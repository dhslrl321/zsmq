package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.support.ZolaConsumer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;

public class ZolaBeanFinder {

    public Map<String, Object> getZolaBeans(ApplicationContext context) {
        return context.getBeansWithAnnotation(ZolaConsumer.class);
    }
}
