package com.wonit.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class Payload {
    String value;
}
