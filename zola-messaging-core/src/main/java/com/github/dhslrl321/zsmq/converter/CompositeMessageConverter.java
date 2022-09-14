package com.github.dhslrl321.zsmq.converter;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import java.util.ArrayList;
import java.util.List;

public class CompositeMessageConverter implements MessageConverter, MessageConverterRegistrar {
    private final List<MessageConverter> registeredConverters = new ArrayList<>();

    @Override
    public ZolaMessage toMessage(String queueName, Object payload) {
        MessageConverter converter = registeredConverters.stream()
                .filter(i -> i.isSupport(payload))
                .findFirst()
                .orElseThrow();
        return converter.toMessage(queueName, payload);
    }

    @Override
    public String fromMessage(ZolaMessage message) {
        MessageConverter converter = registeredConverters.stream()
                .filter(i -> i.isSupport(message))
                .findFirst()
                .orElseThrow();
        return converter.fromMessage(message);
    }

    @Override
    public void register(MessageConverter converter) {
        registeredConverters.add(converter);
    }

    @Override
    public boolean isSupport(Object payload) {
        throw new UnsupportedOperationException("registrar does not provides isSupport() operation");
    }

    @Override
    public boolean isSupport(ZolaMessage message) {
        throw new UnsupportedOperationException("registrar does not provides isSupport() operation");
    }
}
