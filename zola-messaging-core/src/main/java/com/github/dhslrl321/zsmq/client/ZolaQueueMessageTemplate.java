package com.github.dhslrl321.zsmq.client;

import com.github.dhslrl321.zsmq.converter.MessageConverter;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.http.ZolaServerConnectionFailedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZolaQueueMessageTemplate {

    private final ZolaClientConfig config;
    private final ZolaHttpClient httpClient;

    public void convertAndSend(String queueName, Object payload) {
        ZolaMessage message = convert(queueName, payload);
        send(message);
    }

    private ZolaMessage convert(String queueName, Object payload) {
        MessageConverter converter = config.getConverter();
        return converter.toMessage(queueName, payload);
    }

    private void send(ZolaMessage message) {
        boolean send = post(message);
        if (!send) {
            throw new ZolaServerConnectionFailedException("message produce failed");
        }
    }

    private boolean post(ZolaMessage message) {
        return httpClient.requestPush(config.getServerBaseUrl(), message);
    }
}
