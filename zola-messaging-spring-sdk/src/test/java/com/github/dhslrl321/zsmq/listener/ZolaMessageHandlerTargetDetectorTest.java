package com.github.dhslrl321.zsmq.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.util.Pair;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ZolaMessageHandlerTargetDetectorTest {

    ZolaMessageHandlerDetector sut = new ZolaMessageHandlerDetector();

    @ZolaConsumer
    static class Foo {
        @ZolaMessageListener(queueName = "ANY_QUEUE_NAME")
        public void fooMethod(String message) {
        }
    }

    @Test
    void happy_case() throws Exception {
        Class<Foo> fooClass = Foo.class;
        Method barMethod = fooClass.getMethod("fooMethod", String.class);;
        Pair<MessageHandlerTarget, ListeningInformation> pair = Pair.of(MessageHandlerTarget.of(fooClass, barMethod),
                ListeningInformation.of("ANY_QUEUE_NAME"));

        List<Pair<MessageHandlerTarget, ListeningInformation>> actual = sut.detect(Map.of("someClass", new Foo()));

        assertThat(actual.get(0)).isEqualTo(pair);
    }


    @ZolaConsumer
    static class Bar {
        public void barMethod(String message) {
        }
    }

    @Test
    void not_return_pair_when_no_zolaMessageListener_annotation() {
        List<Pair<MessageHandlerTarget, ListeningInformation>> actual = sut.detect(Map.of("someClass", new Bar()));

        assertThat(actual.size()).isZero();
    }


    @ZolaConsumer
    static class Baz {
        @ZolaMessageListener(queueName = "")
        public void bazMethod(String message) {
        }
    }

    @Test
    void throw_when_empty_queueName() {
        assertThatThrownBy(() -> sut.detect(Map.of("someClass", new Baz())))
                .isInstanceOf(InvalidUseOfZolaMessageListenerException.class);
    }


    @ZolaConsumer
    static class Qux {
        @ZolaMessageListener(queueName = "ANY_QUEUE_NAME")
        public void quxMethod() {
        }
    }

    @Test
    void throw_when_missing_parameter() {
        assertThatThrownBy(() -> sut.detect(Map.of("someClass", new Qux())))
                .isInstanceOf(InvalidUseOfZolaMessageListenerException.class);
    }


    @ZolaConsumer
    static class Qux2 {
        @ZolaMessageListener(queueName = "ANY_QUEUE_NAME")
        public void qux2Method(int message) {
        }
    }

    @Test
    void throw_when_not_string_parameter() {
        assertThatThrownBy(() -> sut.detect(Map.of("someClass", new Qux2())))
                .isInstanceOf(InvalidUseOfZolaMessageListenerException.class);
    }
}