package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ThreadUtils;

@RequiredArgsConstructor
public class ListeningTask implements Runnable {

    @Getter
    private final ListeningStrategy strategy;
    private final MessageListener listener;
    @Getter
    private final ListeningInformation listeningInfo;

    @Override
    public void run() {
        boolean loop = true;
        while(loop) {
            try {
                ZolaMessage message = strategy.peek(listeningInfo);
                if (Objects.isNull(message)) {
                    continue;
                }
                listener.listen(message.getPayload().getValue());
                handleAcknowledgement();
                sleep();
            } catch (Exception e) {
                loop = false;
            }
        }
    }

    private void sleep() throws InterruptedException {
        ThreadUtils.sleep(Duration.of(1, ChronoUnit.SECONDS));
    }

    private void handleAcknowledgement() {
        if (listeningInfo.isSameDeletionPolicy(DeletionPolicy.ALWAYS)) {
            strategy.acknowledgement(listeningInfo);
        }
    }
}
