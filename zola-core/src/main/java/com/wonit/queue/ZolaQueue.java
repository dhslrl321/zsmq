package com.wonit.queue;

import com.wonit.message.Message;
import com.wonit.queue.value.QueueName;

public interface ZolaQueue {
    Message peek();
    Message pop();
    void push(Message message);
    int size();

    QueueName getName();
}
