package com.github.dhslrl321.zsmq.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaPayload {
    String value;
}
