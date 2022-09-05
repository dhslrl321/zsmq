package com.github.dhslrl321.zsmq.converter;

import static com.github.dhslrl321.zsmq.message.MediaTypes.JSON;

import com.github.dhslrl321.zsmq.message.MediaTypes;
import com.github.dhslrl321.zsmq.message.ZolaHeader;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import com.github.dhslrl321.zsmq.message.ZolaPayload;
import com.github.dhslrl321.zsmq.queue.QueueName;
import com.google.gson.Gson;
import java.time.LocalDateTime;

public class JsonMessageConverter implements MessageConverter {

    private final Gson gson = new Gson();

    @Override
    public ZolaMessage toMessage(String queueName, Object payload) {
        ZolaHeader header = ZolaHeader.of(QueueName.of(queueName), LocalDateTime.now(), MediaTypes.JSON);
        ZolaPayload zolaPayload = ZolaPayload.of(gson.toJson(payload));
        return ZolaMessage.of(header, zolaPayload);
    }

    @Override
    public String fromMessage(ZolaMessage message) {
        return null;
    }

    @Override
    public boolean isSupport(Object payload) {
        // Always true if not string. Because we only support json or simple string
        if (payload instanceof String) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isSupport(ZolaMessage message) {
        return JSON.equals(message.getMediaType());
    }
}
