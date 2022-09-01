package com.wonit.queue;

import com.wonit.exception.EmptyQueueException;
import com.wonit.message.Message;
import com.wonit.queue.value.QueueName;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleZolaQueue implements ZolaQueue {

    public static SimpleZolaQueue newInstance(QueueName queueName) {
        return new SimpleZolaQueue(queueName, LocalDateTime.now());
    }

    private final List<Message> queue = new ArrayList<>();

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

    @Override
    public Message peek() {
        throwWhenEmpty();
        return queue.get(0);
    }

    @Override
    public Message pop() {
        throwWhenEmpty();
        return queue.remove(0);
    }

    @Override
    public void push(Message message) {
        queue.add(message);
    }

    @Override
    public int size() {
        return queue.size();
    }

    private void throwWhenEmpty() {
        if (queue.isEmpty()) {
            throw new EmptyQueueException("Zola Queue is empty!");
        }
    }
}
