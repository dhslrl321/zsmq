package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.detector.MessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.strategy.HttpPollingListeningStrategy;
import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZolaMessageListeningProcessor {
    private final MessageListenerDetector detector;
    private final ListeningTaskExecutor taskExecutor;

    public void doProcess() {
        List<Pair<MessageListener, ListeningInformation>> listeners = detector.detect();
        List<ListeningTask> tasks = getTasks(listeners);
        listenAll(tasks);
    }

    private List<ListeningTask> getTasks(List<Pair<MessageListener, ListeningInformation>> listenerPairs) {
        return listenerPairs.stream()
                .map(i -> new ListeningTask(new HttpPollingListeningStrategy(), i.getLeft(), i.getRight()))
                .collect(Collectors.toList());
    }

    private void listenAll(List<ListeningTask> tasks) {
        // fixme : 1. possible to be zombie thread
        // fixme : 2. need to be strategy
        while (true) {
            taskExecutor.executeAll(tasks);
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
