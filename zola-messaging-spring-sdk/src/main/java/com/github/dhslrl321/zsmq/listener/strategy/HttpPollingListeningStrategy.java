package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpPollingListeningStrategy implements ListeningStrategy {

    private final ZolaHttpClient httpClient = new ZolaHttpClient();

    @Override
    public ZolaMessage peek(String server, String queueName) {
        Optional<ZolaMessage> optionalMessage = httpClient.requestPeek(server, queueName);

        if (optionalMessage.isEmpty()) {
            return null;
        }

        return optionalMessage.get();
    }

    @Override
    public boolean ack(String server, String queueName) {
        return false;
    }
}
