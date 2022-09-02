package com.github.dhslrl321.zsmq.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AbstractZolaQueueTest {
    AbstractZolaQueue sut = ZolaSimpleQueue.newInstance(QueueName.of("hello"));

    @Test
    void name() {
        int size = sut.size();

        assertThat(size).isZero();
    }
}