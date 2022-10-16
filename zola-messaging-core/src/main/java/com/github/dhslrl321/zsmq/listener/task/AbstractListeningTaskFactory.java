package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractListeningTaskFactory implements ListeningTaskFactory {

    @Override
    public List<ListeningTask> createBy(List<Pair<MessageListener, ListeningInformation>> pairs) {
        return pairs.stream()
                .map(this::createBy)
                .collect(Collectors.toList());
    }

    protected abstract ListeningTask createBy(Pair<MessageListener, ListeningInformation> pair);
}
