package com.wonit.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wonit.queue.value.QueueName;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class ZolaQueueDispatcherTest {

    public static final QueueName FOO_QUEUE_NAME = QueueName.of("foo");
    public static final QueueName BAR_QUEUE_NAME = QueueName.of("bar");
    ZolaQueueDispatcher sut = new ZolaQueueDispatcher();

    @Test
    void can_register() {
        sut.register(SimpleZolaQueue.newInstance(QueueName.of("foo")));
        assertThat(sut.size()).isEqualTo(1);

        sut.register(SimpleZolaQueue.newInstance(QueueName.of("bar")));
        assertThat(sut.size()).isEqualTo(2);
    }

    @Test
    void unique_by_name() {
        sut.register(SimpleZolaQueue.newInstance(QueueName.of("foo")));
        assertThat(sut.size()).isEqualTo(1);

        assertThatThrownBy(() -> sut.register(SimpleZolaQueue.newInstance(QueueName.of("foo"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void peek_by_name() {
        sut.register(SimpleZolaQueue.newInstance(FOO_QUEUE_NAME));
        sut.register(SimpleZolaQueue.newInstance(BAR_QUEUE_NAME));

        ZolaQueue actual = sut.peekBy(FOO_QUEUE_NAME);

        assertThat(actual.getName()).isEqualTo(FOO_QUEUE_NAME);
    }

    @Test
    void name3() {
        assertThatThrownBy(() -> sut.peekBy(FOO_QUEUE_NAME))
                .isInstanceOf(NoSuchElementException.class);
    }
}