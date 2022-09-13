package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.commons.Serializer;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
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
            ZolaMessage message = strategy.peek(queueName);
            if (message == null) {
                // TODO do nothing
                return;
            }
            listener.listen(Serializer.serialize(message));
            // strategy.ack(queueName);
    }
}
