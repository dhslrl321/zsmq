package com.github.dhslrl321.zsmq.core.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.github.dhslrl321.zsmq.exception.EmptyQueueException;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ZolaSimpleQueueTest {

    static final QueueName ANY_NAME = QueueName.of("ANY_NAME");
    static final ZolaPayload ANY_ZOLA_PAYLOAD = ZolaPayload.of("");
    ZolaSimpleQueue sut = ZolaSimpleQueue.newInstance(ANY_NAME);

    ZolaMessage msg1 = ZolaMessage.of(ZolaHeader.of(ANY_NAME, LocalDateTime.now(), null), ANY_ZOLA_PAYLOAD);
    ZolaMessage msg2 = ZolaMessage.of(ZolaHeader.of(ANY_NAME, LocalDateTime.now(), null), ANY_ZOLA_PAYLOAD);

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
    void if_empty_peek_return_null() {
        ZolaMessage actual = sut.peek();
        assertThat(actual).isNull();
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