package com.github.dhslrl321.zsmq.queue;

import com.github.dhslrl321.zsmq.exception.EmptyQueueException;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
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

    private final List<ZolaMessage> queue = new ArrayList<>();

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
    public ZolaMessage peek() {
        throwWhenEmpty();
        return queue.get(0);
    }

    @Override
    public ZolaMessage pop() {
        throwWhenEmpty();
        return queue.remove(0);
    }

    @Override
    public void push(ZolaMessage zolaMessage) {
        queue.add(zolaMessage);
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
