package com.github.dhslrl321.zsmq.detector;

import com.github.dhslrl321.zsmq.annotation.ZolaMessageListener;
import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.InvalidUseOfZolaMessageListenerException;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.SpringBeanMessageListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class SpringBeanMessageListenerDetector implements MessageListenerDetector {

    private final Map<String, Object> beans;
    private final ZolaClientConfig config;

    @SneakyThrows
    @Override
    public List<Pair<MessageListener, ListeningInformation>> detect() {
        final List<Pair<MessageListener, ListeningInformation>> pairs = new ArrayList<>();
        for (Object o : beans.values()) {
            Class<?> aClass = o.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ZolaMessageListener.class)) {
                    ZolaMessageListener annotation = method.getAnnotation(ZolaMessageListener.class);
                    validateListenerAnnotation(method, annotation);

                    Class<?>[] parameterTypes = method.getParameterTypes();
                    throwIfNotSupportParamType(parameterTypes);

                    pairs.add(Pair.of(SpringBeanMessageListener.of(o, method),
                            ListeningInformation.of(config.getServerBaseUrl(), annotation.queueName(), annotation.deletionPolicy())));
                }
            }
        }
        return pairs;
    }

    private void throwIfNotSupportParamType(Class<?>[] parameterTypes) {
        for (Class<?> parameterType : parameterTypes) {
            if (!parameterType.equals(String.class)) {
                throw new InvalidUseOfZolaMessageListenerException(
                        "listener method's parameter have to be String!");
            }
        }
    }

    private void validateListenerAnnotation(Method method, ZolaMessageListener annotation) {
        if (annotation.queueName().isBlank() || method.getParameters().length == 0) {
            throw new InvalidUseOfZolaMessageListenerException("queueName should be not blank");
        }
    }
}
