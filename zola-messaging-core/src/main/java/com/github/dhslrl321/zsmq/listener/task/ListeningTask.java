package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListeningTask implements Runnable {

    @Getter
    private final ListeningStrategy strategy;
    private final MessageListener listener;
    @Getter
    private final ListeningInformation listeningInformation;

    @Override
    public void run() {
        String queueName = listeningInformation.getQueueName();
        String server = listeningInformation.getServer();

        ZolaMessage message = strategy.peek(server, queueName);
        if (message == null) {
            return;
        }
        listener.listen(message.getPayload().getValue());
        if (listeningInformation.getDeletionPolicy().equals(DeletionPolicy.ALWAYS)) {
            strategy.ack(listeningInformation.getServer(), queueName);
        }
    }
}
