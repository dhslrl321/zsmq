package com.wonit.zola.queue;

import com.wonit.zola.message.ZolaMessage;
import java.time.LocalDateTime;

public interface ZolaQueue {
    ZolaMessage peek();

    ZolaMessage pop();

    void push(ZolaMessage zolaMessage);

    int size();

    QueueName getName();

    LocalDateTime getCreatedAt();
}
