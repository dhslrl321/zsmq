package com.wonit.queue;

import com.wonit.queue.value.QueueName;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ZolaQueueDispatcher {

    private final Map<QueueName, ZolaQueue> container = new HashMap<>();

    public void register(ZolaQueue queue) {
        if (container.containsKey(queue.getName())) {
            String message = String.format("A queue named [%s] already exists. ", queue.getName().getValue());
            throw new IllegalArgumentException(message);
        }

        container.put(queue.getName(), queue);
    }

    protected int size() {
        return container.size();
    }

    public ZolaQueue peekBy(QueueName queueName) {
        if (!container.containsKey(queueName)) {
            String message = String.format("A queue [%s] is not registered.", queueName.getValue());
            throw new NoSuchElementException(message);
        }

        return container.get(queueName);
    }
}
