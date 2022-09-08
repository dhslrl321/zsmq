package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.message.ZolaMessage;

public interface ListeningStrategy {
    ZolaMessage peek(String queueName);
    boolean ack(String queueName);
}
