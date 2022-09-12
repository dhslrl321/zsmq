package com.github.dhslrl321.zsmq.listener.task;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolListeningExecutor implements ListeningTaskExecutor {

    // TODO @check does setting proper
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public void executeAll(List<ListeningTask> tasks) {
        tasks.forEach(executor::execute);
    }
}
