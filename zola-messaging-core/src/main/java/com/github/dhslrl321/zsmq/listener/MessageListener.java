package com.github.dhslrl321.zsmq.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.Value;

public interface MessageListener {
    void listen(String message);
}
