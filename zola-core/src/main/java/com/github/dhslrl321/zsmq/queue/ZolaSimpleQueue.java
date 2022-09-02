package com.github.dhslrl321.zsmq.queue;

import com.github.dhslrl321.zsmq.exception.EmptyQueueException;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZolaSimpleQueue extends AbstractZolaQueue {

    public static ZolaSimpleQueue newInstance(QueueName queueName) {
        return new ZolaSimpleQueue(queueName);
    }

    // private final List<ZolaMessage> queue = new ArrayList<>();
    private final Queue<ZolaMessage> queue = new LinkedList<>();

    private ZolaSimpleQueue(QueueName name) {
        super(name, LocalDateTime.now());
    }

    @Override
    public ZolaMessage peek() {
        throwWhenEmpty();
        return queue.peek();
    }

    @Override
    public ZolaMessage pop() {
        throwWhenEmpty();
        return queue.poll();
    }

    @Override
    public void push(ZolaMessage zolaMessage) {
        queue.offer(zolaMessage);
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
