package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.util.Pair;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListeningTask implements Runnable {

    private final Pair<MessageHandlerTarget, ListeningInformation> pair;

    @Override
    public void run() {
        MessageHandlerTarget handler = pair.getLeft();
        ListeningInformation information = pair.getRight();
        System.out.println("information = " + information);

        try {
            //`handler.getMethod().invoke(object, "aa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
