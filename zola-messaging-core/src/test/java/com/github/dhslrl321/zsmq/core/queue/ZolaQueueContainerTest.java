package com.github.dhslrl321.zsmq.core.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZolaQueueContainerTest {

    public static final QueueName FOO_QUEUE_NAME = QueueName.of("foo");
    public static final ZolaSimpleQueue FOO_ZOLA_QUEUE = ZolaSimpleQueue.newInstance(FOO_QUEUE_NAME);

    ZolaQueueContainer sut;

    @BeforeEach
    void setUp() {
        sut = new ZolaQueueContainer();
    }

    @Test
    void name() {
        QueueDescribe actual = sut.describe();

        assertThat(actual).isNotNull();
    }

    @Test
    void can_register() {
        sut.register(FOO_ZOLA_QUEUE);
        assertThat(sut.size()).isEqualTo(1);

        sut.register(ZolaSimpleQueue.newInstance(QueueName.of("bar")));
        assertThat(sut.size()).isEqualTo(2);
    }

    @Test
    void unique_by_name() {
        sut.register(FOO_ZOLA_QUEUE);
        assertThat(sut.size()).isEqualTo(1);

        assertThatThrownBy(() -> sut.register(FOO_ZOLA_QUEUE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void peek_by_name() {
        ZolaMessage zolaMessage = ZolaMessage.of(ZolaHeader.of(QueueName.of(""), LocalDateTime.now(), null), ZolaPayload.of("hello"));
        FOO_ZOLA_QUEUE.push(zolaMessage);
        sut.register(FOO_ZOLA_QUEUE);

        ZolaMessage actual = sut.peekBy(FOO_QUEUE_NAME);

        assertThat(actual.getPayload()).isEqualTo(ZolaPayload.of("hello"));
    }

    @Test
    void throw_when_not_exist() {
        assertThatThrownBy(() -> sut.peekBy(FOO_QUEUE_NAME))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void isContain_by_name() {
        sut.register(ZolaSimpleQueue.newInstance(QueueName.of("Q_NAME")));

        boolean actual = sut.contains("Q_NAME");

        assertThat(actual).isTrue();
    }

    @Test
    void isContain_by_name_not_exist() {
        boolean actual = sut.contains("Q_NAME");

        assertThat(actual).isFalse();
    }

    @Test
    void remove() {
        sut.register(ZolaSimpleQueue.newInstance(QueueName.of("Q-NAME")));
        assertThat(sut.size()).isEqualTo(1);

        sut.removeBy("Q-NAME");
        assertThat(sut.size()).isEqualTo(0);
    }

    @Test
    void remove_fail() {
        assertThatThrownBy(() -> sut.removeBy("Q-NAME"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}