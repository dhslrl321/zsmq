package com.github.dhslrl321.zsmq.queue;

import com.github.dhslrl321.zsmq.message.ZolaMessage;
import java.time.LocalDateTime;

public interface ZolaQueue {
    ZolaMessage peek();

    ZolaMessage pop();

    void push(ZolaMessage zolaMessage);

    int size();

    QueueName getName();

    LocalDateTime getCreatedAt();
}
