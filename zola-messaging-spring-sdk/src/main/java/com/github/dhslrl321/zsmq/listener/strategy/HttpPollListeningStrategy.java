package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.model.PolledMessage;

public class HttpPollListeningStrategy implements ListeningStrategy {
    @Override
    public PolledMessage peek(String queueName) {
        return PolledMessage.of(null, null);
    }

    @Override
    public String pop(String queueName) {
        return null;
    }
}
