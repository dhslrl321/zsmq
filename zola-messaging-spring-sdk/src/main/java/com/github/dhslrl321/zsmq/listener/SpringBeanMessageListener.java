package com.github.dhslrl321.zsmq.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.Value;

@Value(staticConstructor = "of")
public class SpringBeanMessageListener implements MessageListener {
    Object object;
    Method method;

    @Override
    public void listen(String serialized) {
        try {
            method.invoke(object, serialized);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
