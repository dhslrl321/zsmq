package com.github.dhslrl321.zsmq.client;

import com.github.dhslrl321.zsmq.converter.MessageConverter;
import com.github.dhslrl321.zsmq.converter.MessageConverters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor
public class ZolaClientConfig {
    @Getter
    private final String serverBaseUrl;
}
