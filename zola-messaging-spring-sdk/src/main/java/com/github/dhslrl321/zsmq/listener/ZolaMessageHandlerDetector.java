package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.util.Pair;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;

public class ZolaMessageHandlerDetector {

    @SneakyThrows
    public List<Pair<MessageHandlerTarget, ListeningInformation>> detect(Map<String, Object> beans) {
        List<Pair<MessageHandlerTarget, ListeningInformation>> pairs = new ArrayList<>();
        for (Object o : beans.values()) {
            Class<?> aClass = o.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ZolaMessageListener.class)) {
                    ZolaMessageListener annotation = method.getAnnotation(ZolaMessageListener.class);
                    if (annotation.queueName().isBlank() || method.getParameters().length == 0) {
                        throw new InvalidUseOfZolaMessageListenerException("");
                    }

                    Class<?>[] parameterTypes = method.getParameterTypes();
                    for (Class<?> parameterType : parameterTypes) {
                        if (!parameterType.equals(String.class)) {
                            throw new InvalidUseOfZolaMessageListenerException("");
                        }
                    }

                    pairs.add(Pair.of(MessageHandlerTarget.of(o, method),
                            ListeningInformation.of(annotation.queueName())));
                }
            }
        }
        return pairs;
    }
}
