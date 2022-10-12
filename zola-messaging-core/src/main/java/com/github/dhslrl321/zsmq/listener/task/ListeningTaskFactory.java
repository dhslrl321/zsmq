package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import java.util.List;

public interface ListeningTaskFactory {
    List<ListeningTask> createBy(List<Pair<MessageListener, ListeningInformation>> pairs);
}
