package com.wonit.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wonit.exception.EmptyQueueException;
import com.wonit.message.Header;
import com.wonit.message.Message;
import com.wonit.message.Payload;
import com.wonit.queue.value.QueueName;
import org.junit.jupiter.api.Test;

class SimpleZolaQueueTest {

    SimpleZolaQueue sut = SimpleZolaQueue.newInstance(QueueName.of("ANY_NAME"));

    Message msg1 = Message.of(new Header(), new Payload());
    Message msg2 = Message.of(new Header(), new Payload());

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