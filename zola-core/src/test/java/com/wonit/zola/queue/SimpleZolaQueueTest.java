package com.wonit.zola.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wonit.zola.exception.EmptyQueueException;
import com.wonit.zola.message.Header;
import com.wonit.zola.message.Message;
import com.wonit.zola.message.Payload;
import com.wonit.zola.queue.SimpleZolaQueue;
import com.wonit.zola.queue.value.QueueName;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class SimpleZolaQueueTest {

    static final QueueName ANY_NAME = QueueName.of("ANY_NAME");
    static final Payload ANY_PAYLOAD = Payload.of("");
    SimpleZolaQueue sut = SimpleZolaQueue.newInstance(ANY_NAME);

    Message msg1 = Message.of(Header.of(ANY_NAME, LocalDateTime.now()), ANY_PAYLOAD);
    Message msg2 = Message.of(Header.of(ANY_NAME, LocalDateTime.now()), ANY_PAYLOAD);

    @Test
    void added_when_push() {
        sut.push(msg1);
        assertThat(sut.size()).isEqualTo(1);

        sut.push(msg2);
        assertThat(sut.size()).isEqualTo(2);
    }

    @Test
    void peek_with_order() {
        initSUTWithOrder(msg1, msg2);

        Message actual = sut.peek();

        assertThat(actual).isEqualTo(msg1);
    }

    @Test
    void pop_always_return_last_added() {

        initSUTWithOrder(msg1, msg2);

        Message actual = sut.pop();
        assertThat(actual).isEqualTo(msg1);

        Message actual2 = sut.pop();
        assertThat(actual2).isEqualTo(msg2);
    }

    @Test
    void if_empty_peek_throw_exception() {
        assertThatThrownBy(() -> sut.peek()).isInstanceOf(EmptyQueueException.class);
    }

    @Test
    void if_empty_pop_throw_exception() {
        assertThatThrownBy(() -> sut.pop()).isInstanceOf(EmptyQueueException.class);
    }

    private void initSUTWithOrder(Message msg1, Message msg2) {
        sut.push(msg1);
        sut.push(msg2);
    }
}