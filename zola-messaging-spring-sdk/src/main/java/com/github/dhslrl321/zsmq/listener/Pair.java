package com.github.dhslrl321.zsmq.listener;

import lombok.Value;

@Value(staticConstructor = "of")
public class Pair<LEFT, RIGHT> {
    LEFT left;
    RIGHT right;
}
