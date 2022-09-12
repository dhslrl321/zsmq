package com.github.dhslrl321.zsmq.core.queue;

import java.time.LocalDateTime;

public interface Describable {
    QueueName getName();

    LocalDateTime getCreatedAt();

    int size();
}
