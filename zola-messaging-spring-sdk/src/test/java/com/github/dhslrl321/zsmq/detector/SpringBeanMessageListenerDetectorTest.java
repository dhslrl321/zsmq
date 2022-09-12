package com.github.dhslrl321.zsmq.detector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.listener.InvalidUseOfZolaMessageListenerException;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.commons.Pair;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpringBeanMessageListenerDetectorTest {

    SpringBeanMessageListenerDetector sut;

    ListenerBeanFinder finder = mock(ListenerBeanFinder.class);

    @BeforeEach
    void setUp() {
        sut = new SpringBeanMessageListenerDetector(finder);
    }

    @EqualsAndHashCode
    @ZolaConsumer
    static class Foo {
        @ZolaMessageListener(queueName = "ANY_QUEUE_NAME")
        public void fooMethod(String message) {
        }
    }

    @Test
    void happy_case() throws Exception {
        Foo fooClass = new Foo();
        Method barMethod = fooClass.getClass().getMethod("fooMethod", String.class);
        ;
        Pair<MessageListener, ListeningInformation> pair = Pair.of(MessageListener.of(fooClass, barMethod),
                ListeningInformation.of("ANY_QUEUE_NAME"));

        given(finder.findZolaBeans()).willReturn(Map.of("someClass", new Foo()));

        List<Pair<MessageListener, ListeningInformation>> actual = sut.detect();

        assertThat(actual.get(0)).isEqualTo(pair);
    }


    @ZolaConsumer
    static class Bar {
        public void barMethod(String message) {
        }
    }

    @Test
    void not_return_pair_when_no_zolaMessageListener_annotation() {
        given(finder.findZolaBeans()).willReturn(Map.of("someClass", new Bar()));
        List<Pair<MessageListener, ListeningInformation>> actual = sut.detect();

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
        given(finder.findZolaBeans()).willReturn(Map.of("someClass", new Baz()));
        assertThatThrownBy(() -> sut.detect())
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
        given(finder.findZolaBeans()).willReturn(Map.of("someClass", new Qux()));
        assertThatThrownBy(() -> sut.detect())
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
        given(finder.findZolaBeans()).willReturn(Map.of("someClass", new Qux2()));
        assertThatThrownBy(() -> sut.detect())
                .isInstanceOf(InvalidUseOfZolaMessageListenerException.class);
    }
}