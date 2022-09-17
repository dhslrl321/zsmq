package com.github.dhslrl321.zsmq.core.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaMessage {
    ZolaHeader header;
    ZolaPayload payload;

    public MediaTypes getMediaType() {
        return header.getMediaType();
    }
}
