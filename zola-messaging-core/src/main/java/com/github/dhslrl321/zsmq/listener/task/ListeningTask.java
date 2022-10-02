package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListeningTask implements Runnable {

    @Getter
    private final ListeningStrategy strategy;
    private final MessageListener listener;
    @Getter
    private final ListeningInformation listeningInfo;

    @Override
    public void run() {
        while(true) {
            ZolaMessage message = strategy.peek(listeningInfo);
            listener.listen(message.getPayload().getValue());
            handleAcknowledgement();
        }
    }

    private void handleAcknowledgement() {
        if (listeningInfo.isSameDeletionPolicy(DeletionPolicy.ALWAYS)) {
            strategy.acknowledgement(listeningInfo);
        }
    }
}
