package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.model.PolledMessage;

public interface ListeningStrategy {
    PolledMessage peek(String queueName);
    String pop(String queueName);
}
