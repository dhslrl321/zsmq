package com.github.dhslrl321.zsmq.converter;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;

public interface MessageConverter extends Supportable {

    ZolaMessage toMessage(String queueName, Object payload);

    String fromMessage(ZolaMessage message);
    default <T> T fromMessage(ZolaMessage message, T type) {
        return null;
    }
}
