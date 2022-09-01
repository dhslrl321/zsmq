package com.wonit.zola.queue;

import com.wonit.zola.message.Message;
import com.wonit.zola.queue.value.QueueInfo;
import com.wonit.zola.queue.value.QueueName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ZolaQueueDispatcher {

    private final Map<QueueName, ZolaQueue> container = new HashMap<>();

    public void register(ZolaQueue queue) {
        throwWhenNotExist(queue);

        container.put(queue.getName(), queue);
    }

    protected int size() {
        return container.size();
    }

    public Message peekBy(QueueName queueName) {
        throwWhenNotExist(queueName);

        return get(queueName).peek();
    }

    // TODO test
    public void pushBy(Message message) {
        QueueName queueName = message.getHeader().getQueueName();
        throwWhenNotExist(queueName);
        get(queueName).push(message);
    }

    // TODO test
    public void popBy(QueueName queueName) {
        throwWhenNotExist(queueName);

        get(queueName).pop();
    }

    protected ZolaQueue get(QueueName queueName) {
        throwWhenNotExist(queueName);
        return container.get(queueName);
    }

    private void throwWhenNotExist(QueueName queueName) {
        if (!container.containsKey(queueName)) {
            String msg = String.format("A queue [%s] is not registered.", queueName.getValue());
            throw new NoSuchElementException(msg);
        }
    }

    private void throwWhenNotExist(ZolaQueue queue) {
        if (container.containsKey(queue.getName())) {
            String message = String.format("A queue named [%s] already exists. ", queue.getName().getValue());
            throw new IllegalArgumentException(message);
        }
    }

    public QueueDescribe describe() {
        List<QueueInfo> info = container.values().stream()
                .map(this::getInfoBy)
                .collect(Collectors.toList());
        return QueueDescribe.of(container.size(), info);
    }

    private QueueInfo getInfoBy(ZolaQueue zolaQueue) {
        return QueueInfo.of(zolaQueue.getName().getValue(), zolaQueue.size(),
                zolaQueue.getCreatedAt());
    }
}
