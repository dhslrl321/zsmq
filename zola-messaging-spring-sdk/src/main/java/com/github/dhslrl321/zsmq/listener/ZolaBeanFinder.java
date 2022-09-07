package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import java.util.Map;
import org.springframework.context.ApplicationContext;

public class ZolaBeanFinder {

    public Map<String, Object> getZolaBeans(ApplicationContext context) {
        return context.getBeansWithAnnotation(ZolaConsumer.class);
    }
}
