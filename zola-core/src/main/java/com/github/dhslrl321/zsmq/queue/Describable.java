package com.github.dhslrl321.zsmq.queue;

import java.time.LocalDateTime;

public interface Describable {
    QueueName getName();

    LocalDateTime getCreatedAt();

    int size();
}
