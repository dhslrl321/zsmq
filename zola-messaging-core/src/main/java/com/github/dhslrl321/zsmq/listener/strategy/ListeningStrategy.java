package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;

public interface ListeningStrategy {
    ZolaMessage peek(String server, String queueName);
    boolean ack(String server, String queueName);
}
