package com.github.dhslrl321.zsmq.core.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaPayload {
    String value;
}
