package com.github.dhslrl321.zsmq.converter;

public class MessageConverters {
    public static CompositeMessageConverter initConverters() {
        CompositeMessageConverter converters = new CompositeMessageConverter();
        converters.register(new StringMessageConverter());
        converters.register(new JsonMessageConverter());
        return converters;
    }
}
