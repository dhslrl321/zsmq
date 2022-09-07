package com.github.dhslrl321.zsmq.listener;

import static java.lang.Thread.sleep;

import com.github.dhslrl321.zsmq.util.Pair;

public class SimpleMessageHandlerExecutor implements MessageHandlerExecutor {
    @Override
    public void execute(Pair<MessageHandlerTarget, ListeningInformation> pair) {
        ListeningTask task = new ListeningTask(pair);
        while(true) {
            try {
                task.run();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
