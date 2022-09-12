package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.detector.MessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import com.github.dhslrl321.zsmq.commons.Pair;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZolaListenerContainer {
    private final MessageListenerDetector detector;
    private final ListeningTaskFactory taskFactory;
    private final ListeningTaskExecutor taskExecutor;

    public void listen() {
        List<Pair<MessageListener, ListeningInformation>> listenerPairs = detector.detect();
        List<ListeningTask> tasks = getTasks(listenerPairs);
        listen(tasks);
    }

    private List<ListeningTask> getTasks(List<Pair<MessageListener, ListeningInformation>> listenerPairs) {
        return listenerPairs.stream()
                .map(taskFactory::createBy)
                .collect(Collectors.toList());
    }

    private void listen(List<ListeningTask> tasks) {
        while(true) {
            taskExecutor.executeAll(tasks);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
