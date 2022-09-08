package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.detector.SimpleMessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import com.github.dhslrl321.zsmq.util.Pair;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZolaListenerContainer {
    private final SimpleMessageListenerDetector detector;
    private final ListeningTaskFactory taskFactory;
    private final ListeningTaskExecutor taskExecutor;

    public void listen() {
        List<Pair<MessageListener, ListeningInformation>> listenerPairs = detector.detect();

        List<ListeningTask> tasks = listenerPairs.stream()
                .map(taskFactory::createBy)
                .collect(Collectors.toList());
        while(true) {
            taskExecutor.executeAll(tasks);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
