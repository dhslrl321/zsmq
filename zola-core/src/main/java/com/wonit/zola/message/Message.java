package com.wonit.zola.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class Message {
    Header header;
    Payload payload;
}
