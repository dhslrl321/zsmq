package com.github.dhslrl321.zsmq.core;

import com.github.dhslrl321.zsmq.ZolaHttpClient;
import com.github.dhslrl321.zsmq.converter.MessageConverter;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZolaQueueMessageTemplate {

    private final ZolaClientConfig config;
    private final ZolaHttpClient httpClient;

    public void convertAndSend(String queueName, Object payload) {
        MessageConverter converter = config.getConverter();
        ZolaMessage message = converter.toMessage(queueName, payload);

        httpClient.post(config.getDestination(), message);
    }
}
