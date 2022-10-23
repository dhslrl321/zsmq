package com.github.dhslrl321.zsmq.converter;

import static com.github.dhslrl321.zsmq.core.message.MediaTypes.JSON;

import com.github.dhslrl321.zsmq.commons.ZolaJsonSerializer;
import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import java.time.LocalDateTime;

class JsonMessageConverter implements MessageConverter {

    @Override
    public ZolaMessage toMessage(String queueName, Object payload) {
        ZolaHeader header = ZolaHeader.of(QueueName.of(queueName), LocalDateTime.now(), MediaTypes.JSON);
        String serialized = ZolaJsonSerializer.getInstance().serialize(payload);
        ZolaPayload zolaPayload = ZolaPayload.of(serialized);
        return ZolaMessage.of(header, zolaPayload);
    }

    @Override
    public String fromMessage(ZolaMessage message) {
        return message.getPayload().getValue();
    }

    @Override
    public boolean isSupport(Object payload) {
        if (payload instanceof String) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isSupport(ZolaMessage message) {
        return message.isSameTypeBy(JSON);
    }
}
