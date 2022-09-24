package com.github.dhslrl321.zsmq.core.message;

import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaMessage {
    ZolaHeader header;
    ZolaPayload payload;

    public boolean isSameTypeBy(MediaTypes mediaTypes) {
        return header.getMediaType().equals(mediaTypes);
    }

    public String getQueueNameValue() {
        return header.getQueueName().getValue();
    }

    public MediaTypes getMediaType() {
        return header.getMediaType();
    }

    public LocalDateTime getTimestamp() {
        return header.getTimestamp();
    }
}
