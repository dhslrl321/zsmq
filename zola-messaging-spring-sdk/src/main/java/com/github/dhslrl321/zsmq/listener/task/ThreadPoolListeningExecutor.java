package com.github.dhslrl321.zsmq.listener.task;

import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolListeningExecutor implements ListeningTaskExecutor {

    // TODO check proper setting
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public void executeAll(List<ListeningTask> tasks) {
        tasks.forEach(executor::execute);
    }
}
