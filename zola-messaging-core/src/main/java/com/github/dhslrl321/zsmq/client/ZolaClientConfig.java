package com.github.dhslrl321.zsmq.client;

import com.github.dhslrl321.zsmq.converter.CompositeMessageConverter;
import com.github.dhslrl321.zsmq.converter.JsonMessageConverter;
import com.github.dhslrl321.zsmq.converter.MessageConverter;
import com.github.dhslrl321.zsmq.converter.StringMessageConverter;
import lombok.Getter;

public class ZolaClientConfig {

    @Getter
    private final String serverBaseUrl;
    @Getter
    private final MessageConverter converter;

    public ZolaClientConfig(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
        converter = initialConverter();
    }

    private MessageConverter initialConverter() {
        CompositeMessageConverter converters = new CompositeMessageConverter();
        converters.register(new StringMessageConverter());
        converters.register(new JsonMessageConverter());
        return converters;
    }
}
