package com.github.dhslrl321.zsmq.converter;

import static com.github.dhslrl321.zsmq.core.message.MediaTypes.TEXT;

import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import java.time.LocalDateTime;

public class StringMessageConverter implements MessageConverter {
    @Override
    public ZolaMessage toMessage(String queueName, Object payload) {
        ZolaHeader header = ZolaHeader.of(QueueName.of(queueName), LocalDateTime.now(), TEXT);
        return ZolaMessage.of(header, ZolaPayload.of((String) payload));
    }

    @Override
    public String fromMessage(ZolaMessage message) {
        return message.getZolaPayload().getValue();
    }

    @Override
    public boolean isSupport(Object payload) {
        return payload instanceof String;
    }

    @Override
    public boolean isSupport(ZolaMessage message) {
        return TEXT.equals(message.getMediaType());
    }
}
