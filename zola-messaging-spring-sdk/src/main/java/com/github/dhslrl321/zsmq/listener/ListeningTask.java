package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import com.github.dhslrl321.zsmq.model.PolledMessage;
import com.github.dhslrl321.zsmq.util.Pair;
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
            PolledMessage message = strategy.peek(queueName);
            listener.getMethod().invoke(invokeTarget, message);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            // TODO add http client exception
        } finally {
            strategy.pop(queueName);
        }
    }
}
