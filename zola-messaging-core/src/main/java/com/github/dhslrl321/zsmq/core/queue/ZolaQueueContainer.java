package com.github.dhslrl321.zsmq.core.queue;

import com.github.dhslrl321.zsmq.commons.Registrable;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.exception.QueueNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ZolaQueueContainer implements Registrable<ZolaQueue> {

    private final Map<QueueName, ZolaQueue> registered = new HashMap<>();

    @Override
    public void register(ZolaQueue queue) {
        throwWhenDuplicated(queue);
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
        throwWhenNotFound(queueName);
        return get(queueName).peek();
    }

    public void pushBy(ZolaMessage zolaMessage) {
        QueueName queueName = zolaMessage.getHeader().getQueueName();
        throwWhenNotFound(queueName);
        get(queueName).push(zolaMessage);
    }

    public void popBy(QueueName queueName) {
        throwWhenNotFound(queueName);
        get(queueName).pop();
    }

    public void removeBy(String queueName) {
        throwWhenNotFound(QueueName.of(queueName));
        registered.remove(QueueName.of(queueName));
    }

    public boolean contains(String queueName) {
        return registered.containsKey(QueueName.of(queueName));
    }

    protected ZolaQueue get(QueueName queueName) {
        throwWhenNotFound(queueName);
        return registered.get(queueName);
    }

    private void throwWhenNotFound(QueueName queueName) {
        if (!registered.containsKey(queueName)) {
            throw new QueueNotFoundException();
        }
    }

    private void throwWhenDuplicated(ZolaQueue queue) {
        if (registered.containsKey(queue.getName())) {
            String message = String.format("A queue named [%s] already exists. ", queue.getName().getValue());
            throw new IllegalArgumentException(message);
        }
    }

    private QueueInfo getInfoBy(ZolaQueue zolaQueue) {
        return QueueInfo.of(zolaQueue.getName().getValue(), zolaQueue.size(), zolaQueue.getCreatedAt());
    }
}
