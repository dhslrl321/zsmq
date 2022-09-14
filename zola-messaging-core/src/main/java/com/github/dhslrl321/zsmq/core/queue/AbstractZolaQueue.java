package com.github.dhslrl321.zsmq.core.queue;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractZolaQueue implements ZolaQueue {

    private final QueueName name;
    private final LocalDateTime createdAt;

    @Override
    public QueueName getName() {
        return name;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
