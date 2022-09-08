package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.commons.Serializer;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import java.lang.reflect.InvocationTargetException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListeningTask implements Runnable {

    @Getter
    private final ListeningStrategy strategy;
    @Getter
    private final ListeningInformation listeningInformation;
    private final MessageListener listener;

    @Override
    public void run() {
        String queueName = listeningInformation.getQueueName();
        Object invokeTarget = listener.getObject();
        try {
            ZolaMessage message = strategy.peek(queueName);
            if (message == null) {
                // TODO do nothing
                return;
            }
            String serialize = Serializer.serialize(message);
            listener.getMethod().invoke(invokeTarget, serialize);
            // strategy.ack(queueName);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            // TODO add http client exception
        }
    }
}
