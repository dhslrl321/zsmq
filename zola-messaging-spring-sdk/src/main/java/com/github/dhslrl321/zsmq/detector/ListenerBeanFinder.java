package com.github.dhslrl321.zsmq.detector;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// @Component
@RequiredArgsConstructor
public class ListenerBeanFinder {

    private final ApplicationContext context;

    public Map<String, Object> findZolaBeans() {
        return context.getBeansWithAnnotation(ZolaConsumer.class);
    }
}
