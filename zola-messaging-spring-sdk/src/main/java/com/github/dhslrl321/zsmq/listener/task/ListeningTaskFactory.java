package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.HttpPollingListeningStrategy;
import java.util.List;
import java.util.stream.Collectors;

public class ListeningTaskFactory {

    public ListeningTask createBy(Pair<MessageListener, ListeningInformation> pair) {
        return new ListeningTask(new HttpPollingListeningStrategy(), pair.getLeft(), pair.getRight());
    }

    public List<ListeningTask> createBy(List<Pair<MessageListener, ListeningInformation>> pairs) {
        return pairs.stream()
                .map(this::createBy)
                .collect(Collectors.toList());
    }
}
