package com.github.dhslrl321.zsmq.listener;

import lombok.Value;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Value(staticConstructor = "of")
public class SpringBeanMessageListener implements MessageListener {
    Object object;
    Method method;

    @Override
    public void listen(String serialized) {
        // listener.getMethod().invoke(invokeTarget, serialize);

        try {
            method.invoke(object, serialized);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
