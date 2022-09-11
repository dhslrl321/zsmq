package com.github.dhslrl321.zsmq.detector;

import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.listener.InvalidUseOfZolaMessageListenerException;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.util.Pair;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class SpringBeanMessageListenerDetector implements MessageListenerDetector {

    private final ListenerBeanFinder finder;

    @SneakyThrows
    @Override
    public List<Pair<MessageListener, ListeningInformation>> detect() {
        final Map<String, Object> beans = finder.getZolaBeans();
        final List<Pair<MessageListener, ListeningInformation>> pairs = new ArrayList<>();
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

                    pairs.add(Pair.of(MessageListener.of(o, method),
                            ListeningInformation.of(annotation.queueName())));
                }
            }
        }
        return pairs;
    }
}
