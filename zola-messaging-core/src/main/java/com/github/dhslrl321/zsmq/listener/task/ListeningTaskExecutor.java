package com.github.dhslrl321.zsmq.listener.task;

import java.util.List;

public interface ListeningTaskExecutor {
    void executeAll(List<ListeningTask> tasks);
}
