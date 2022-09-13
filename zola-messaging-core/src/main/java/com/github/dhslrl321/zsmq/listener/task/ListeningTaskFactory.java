package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.strategy.HttpPollListeningStrategy;
import com.github.dhslrl321.zsmq.listener.strategy.ListeningStrategy;
import com.github.dhslrl321.zsmq.commons.Pair;

public class ListeningTaskFactory {

    public ListeningTask createBy(Pair<MessageListener, ListeningInformation> pair) {
        ListeningStrategy strategy = new HttpPollListeningStrategy();
        return new ListeningTask(strategy, pair.getRight(), pair.getLeft());
    }
}
