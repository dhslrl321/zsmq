package com.github.dhslrl321.zsmq.core.queue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AbstractZolaQueueTest {
    AbstractZolaQueue sut = ZolaSimpleQueue.newInstance(QueueName.of("hello"));

    @Test
    void name() {
        int size = sut.size();

        assertThat(size).isZero();
    }
}