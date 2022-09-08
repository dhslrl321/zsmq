package com.github.dhslrl321.zsmq.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class PolledMessage {
    HeaderModel header;
    String payload;
}
