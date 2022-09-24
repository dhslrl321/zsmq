package com.github.dhslrl321.zsmq.client;

import com.github.dhslrl321.zsmq.converter.MessageConverter;
import com.github.dhslrl321.zsmq.converter.MessageConverters;
import lombok.Getter;

public class ZolaClientConfig {

    @Getter
    private final String serverBaseUrl;
    @Getter
    private final MessageConverter converter;

    public ZolaClientConfig(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
        converter = MessageConverters.initConverters();
    }
}
