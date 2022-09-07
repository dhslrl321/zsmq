package com.github.dhslrl321.zsmq.listener;

import java.lang.reflect.Method;
import lombok.Value;

@Value(staticConstructor = "of")
public class MessageHandlerTarget {
    Object object;
    Method method;
}
