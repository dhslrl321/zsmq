package com.github.dhslrl321.zsmq.converter;

import com.github.dhslrl321.zsmq.message.ZolaMessage;

public interface MessageConverter {

    ZolaMessage toMessage(String queueName, Object payload);

    String fromMessage(ZolaMessage message);

    boolean isSupport(Object payload);
    boolean isSupport(ZolaMessage message);
}
