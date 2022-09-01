package com.wonit.zola.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wonit.zola.exception.EmptyQueueException;
import com.wonit.zola.message.ZolaHeader;
import com.wonit.zola.message.ZolaMessage;
import com.wonit.zola.message.ZolaPayload;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class SimpleZolaQueueTest {

    static final QueueName ANY_NAME = QueueName.of("ANY_NAME");
    static final ZolaPayload ANY_ZOLA_PAYLOAD = ZolaPayload.of("");
    SimpleZolaQueue sut = SimpleZolaQueue.newInstance(ANY_NAME);

    ZolaMessage msg1 = ZolaMessage.of(ZolaHeader.of(ANY_NAME, LocalDateTime.now()), ANY_ZOLA_PAYLOAD);
    ZolaMessage msg2 = ZolaMessage.of(ZolaHeader.of(ANY_NAME, LocalDateTime.now()), ANY_ZOLA_PAYLOAD);

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

        ZolaMessage actual = sut.peek();

        assertThat(actual).isEqualTo(msg1);
    }

    @Test
    void pop_always_return_last_added() {

        initSUTWithOrder(msg1, msg2);

        ZolaMessage actual = sut.pop();
        assertThat(actual).isEqualTo(msg1);

        ZolaMessage actual2 = sut.pop();
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

    private void initSUTWithOrder(ZolaMessage msg1, ZolaMessage msg2) {
        sut.push(msg1);
        sut.push(msg2);
    }
}