package com.github.dhslrl321.zsmq.core.queue;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ZolaQueueContainer {

    private final Map<QueueName, ZolaQueue> registered = new HashMap<>();

    public void register(ZolaQueue queue) {
        throwWhenNotExist(queue);

        registered.put(queue.getName(), queue);
    }

    public QueueDescribe describe() {
        List<QueueInfo> info = registered.values().stream().map(this::getInfoBy).collect(Collectors.toList());
        return QueueDescribe.of(registered.size(), info);
    }

    protected int size() {
        return registered.size();
    }

    public ZolaMessage peekBy(QueueName queueName) {
        throwWhenNotExist(queueName);
        return get(queueName).peek();
    }

    public void pushBy(ZolaMessage zolaMessage) {
        QueueName queueName = zolaMessage.getHeader().getQueueName();
        throwWhenNotExist(queueName);
        get(queueName).push(zolaMessage);
    }

    public void popBy(QueueName queueName) {
        throwWhenNotExist(queueName);
        get(queueName).pop();
    }

    public boolean contains(String queueName) {
        return registered.containsKey(QueueName.of(queueName));
    }

    protected ZolaQueue get(QueueName queueName) {
        throwWhenNotExist(queueName);
        return registered.get(queueName);
    }

    private void throwWhenNotExist(QueueName queueName) {
        if (!registered.containsKey(queueName)) {
            String msg = String.format("A queue [%s] is not registered.", queueName.getValue());
            throw new NoSuchElementException(msg);
        }
    }

    private void throwWhenNotExist(ZolaQueue queue) {
        if (registered.containsKey(queue.getName())) {
            String message = String.format("A queue named [%s] already exists. ", queue.getName().getValue());
            throw new IllegalArgumentException(message);
        }
    }

    private QueueInfo getInfoBy(ZolaQueue zolaQueue) {
        return QueueInfo.of(zolaQueue.getName().getValue(), zolaQueue.size(), zolaQueue.getCreatedAt());
    }

    public void removeBy(String queueName) {
        if (!contains(queueName)) {
            throw new IllegalArgumentException("queue not found");
        }
        registered.remove(QueueName.of(queueName));
    }
}
