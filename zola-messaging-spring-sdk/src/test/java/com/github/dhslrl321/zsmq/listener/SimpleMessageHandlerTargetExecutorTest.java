package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.util.Pair;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

class SimpleMessageHandlerTargetExecutorTest {

    SimpleMessageHandlerExecutor sut = new SimpleMessageHandlerExecutor();

    @ZolaConsumer
    static class Foo {

        @ZolaMessageListener(queueName = "ANY_QUEUE_NAME")
        public void fooMethod(String message) {
            System.out.println("in fooMethod, message = " + message);
        }
    }

    @Test
    void name() throws Exception {
        Method method = Foo.class.getMethod("fooMethod", String.class);
        Pair<MessageHandlerTarget, ListeningInformation> pair = Pair.of(MessageHandlerTarget.of(new Foo(), method),
                ListeningInformation.of("some queue"));
        sut.execute(pair);
    }
}