package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.HttpPollingListeningStrategy;
import java.util.List;
import java.util.stream.Collectors;

public class PollingQueueListeningTaskFactory extends AbstractListeningTaskFactory {
    protected ListeningTask createBy(Pair<MessageListener, ListeningInformation> pair) {
        return new ListeningTask(new HttpPollingListeningStrategy(), pair);
    }
}
