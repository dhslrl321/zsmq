package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import java.util.Optional;

public class HttpPollListeningStrategy implements ListeningStrategy {

    private final ZolaHttpClient httpClient = new ZolaHttpClient();

    @Override
    public ZolaMessage peek(String queueName) {
        Optional<ZolaMessage> optionalMessage = httpClient.requestPeek("http://localhost:8291/");

        if (optionalMessage.isEmpty()) {
            return null;
        }

        return optionalMessage.get();
    }

    @Override
    public boolean ack(String queueName) {
        return httpClient.requestAck(queueName);
    }
}
