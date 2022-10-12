package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.detector.MessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZolaMessageListeningProcessor {
    private final MessageListenerDetector detector;
    private final ListeningTaskFactory taskFactory;
    private final ListeningTaskExecutor taskExecutor;

    public void doProcess() {
        List<Pair<MessageListener, ListeningInformation>> listeners = detector.detect();
        List<ListeningTask> tasks = taskFactory.createBy(listeners);
        taskExecutor.executeAll(tasks);
    }
}
