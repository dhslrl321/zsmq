package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.support.ZolaMessageListener;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class ZolaMessageHandlerDetector {

    @SneakyThrows
    public List<Pair<MessageHandlerMethod, ListeningInformation>> detect(Map<String, Object> beans) {
        return getZolaMessageQueueListenerMethods(beans)
                .map(method -> {
                    ZolaMessageListener annotation = method.getAnnotation(ZolaMessageListener.class);
                    if (annotation.queueName().isBlank() || method.getParameters().length == 0) {
                        throw new InvalidUseOfZolaMessageListenerException("queue name must be present!");
                    }
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    for (Class<?> parameterType : parameterTypes) {
                        if (!parameterType.equals(String.class)) {
                            throw new InvalidUseOfZolaMessageListenerException("ZolaMessageListener method parameter must be String type");
                        }
                    }
                    return Pair.of(MessageHandlerMethod.of(method), ListeningInformation.of(annotation.queueName()));
                }).collect(Collectors.toList());
    }

    private Stream<Method> getZolaMessageQueueListenerMethods(Map<String, Object> beans) {
        return beans.values().stream()
                .map(Object::getClass)
                .map(Class::getMethods)
                .flatMap(Arrays::stream)
                .filter(this::isZolaMessageListener);
    }

    private boolean isZolaMessageListener(Method method) {
        return method.isAnnotationPresent(ZolaMessageListener.class);
    }
}
