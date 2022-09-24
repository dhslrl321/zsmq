package com.github.dhslrl321.zsmq.client;

import static org.valid4j.Validation.validate;

import com.github.dhslrl321.zsmq.converter.MessageConverter;
import com.github.dhslrl321.zsmq.converter.MessageConverters;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.http.ZolaServerConnectionFailedException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class ZolaQueueMessageTemplate {

    private final ZolaClientConfig config;
    private final ZolaHttpClient httpClient;
    private final MessageConverter converter = MessageConverters.initConverters();

    public void convertAndSend(String queueName, Object payload) {
        validate(!StringUtils.isBlank(queueName), new IllegalArgumentException("queue name must not be null or blank"));
        validate(!Objects.isNull(payload), new IllegalArgumentException("payload must not be null"));

        ZolaMessage message = converter.toMessage(queueName, payload);
        send(message);
    }

    private void send(ZolaMessage message) {
        boolean send = request(message);
        if (!send) {
            throw new ZolaServerConnectionFailedException("message produce failed");
        }
    }

    private boolean request(ZolaMessage message) {
        return httpClient.requestPush(config.getServerBaseUrl(), message);
    }
}
