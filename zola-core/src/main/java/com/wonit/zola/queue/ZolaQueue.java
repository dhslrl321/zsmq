package com.wonit.zola.queue;

import com.wonit.zola.message.Message;
import com.wonit.zola.queue.value.QueueName;
import java.time.LocalDateTime;

public interface ZolaQueue {
    Message peek();

    Message pop();

    void push(Message message);

    int size();

    QueueName getName();

    LocalDateTime getCreatedAt();
}
