package com.wonit.zola.message;

import lombok.Value;

@Value(staticConstructor = "of")
public class ZolaMessage {
    ZolaHeader zolaHeader;
    ZolaPayload zolaPayload;
}
