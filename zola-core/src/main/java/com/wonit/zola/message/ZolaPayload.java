package com.wonit.zola.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaPayload {
    String value;
}
