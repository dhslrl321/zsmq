package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.detector.MessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.strategy.HttpPollListeningStrategy;
import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import com.github.dhslrl321.zsmq.commons.Pair;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// @Component
@RequiredArgsConstructor
public class ZolaListenerContainer {
    private final MessageListenerDetector detector;
    private final ListeningTaskExecutor taskExecutor;

    public void listenAll() {
        List<Pair<MessageListener, ListeningInformation>> listenerPairs = detector.detect();
        List<ListeningTask> tasks = getTasks(listenerPairs);
        listenAll(tasks);
    }

    private List<ListeningTask> getTasks(List<Pair<MessageListener, ListeningInformation>> listenerPairs) {
        return listenerPairs.stream()
                .map(i -> new ListeningTask(new HttpPollListeningStrategy(), i.getLeft(), i.getRight()))
                .collect(Collectors.toList());
    }

    private void listenAll(List<ListeningTask> tasks) {
        while(true) {
            taskExecutor.executeAll(tasks);
            try {
                // 얘도 전략이 되어야 할것 같다
                Thread.sleep(5000);
            } catch (Exception e) {

            }
        }
    }
}
