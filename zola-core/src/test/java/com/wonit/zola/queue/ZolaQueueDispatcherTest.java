package com.wonit.zola.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wonit.zola.message.ZolaHeader;
import com.wonit.zola.message.ZolaMessage;
import com.wonit.zola.message.ZolaPayload;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class ZolaQueueDispatcherTest {

    public static final QueueName FOO_QUEUE_NAME = QueueName.of("foo");
    public static final SimpleZolaQueue FOO_ZOLA_QUEUE = SimpleZolaQueue.newInstance(FOO_QUEUE_NAME);

    ZolaQueueDispatcher sut = new ZolaQueueDispatcher();

    @Test
    void name() {
        QueueDescribe actual = sut.describe();

        assertThat(actual).isNotNull();
    }

    @Test
    void can_register() {
        sut.register(FOO_ZOLA_QUEUE);
        assertThat(sut.size()).isEqualTo(1);

        sut.register(SimpleZolaQueue.newInstance(QueueName.of("bar")));
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
        ZolaMessage zolaMessage = ZolaMessage.of(ZolaHeader.of(QueueName.of(""), LocalDateTime.now()), ZolaPayload.of("hello"));
        FOO_ZOLA_QUEUE.push(zolaMessage);
        sut.register(FOO_ZOLA_QUEUE);

        ZolaMessage actual = sut.peekBy(FOO_QUEUE_NAME);

        assertThat(actual.getZolaPayload()).isEqualTo(ZolaPayload.of("hello"));
    }

    @Test
    void throw_when_not_exist() {
        assertThatThrownBy(() -> sut.peekBy(FOO_QUEUE_NAME))
                .isInstanceOf(NoSuchElementException.class);
    }
}