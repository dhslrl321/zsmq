package com.github.dhslrl321.zsmq.listener;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayNameGenerator.Simple;
import org.junit.jupiter.api.Test;

class SimpleMessageHandlerExecutorTest {

    SimpleMessageHandlerExecutor sut;

    @Test
    void name() {
        Pair<MessageHandlerMethod, ListeningInformation> pair = Pair.of(MessageHandlerMethod.of(null),
                ListeningInformation.of("some queue"));
        sut.execute(pair);
    }
}