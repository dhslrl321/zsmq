package com.github.dhslrl321.zsmq.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaMessage {
    ZolaHeader zolaHeader;
    ZolaPayload zolaPayload;

    public MediaTypes getMediaType() {
        return zolaHeader.getMediaTypes();
    }
}
