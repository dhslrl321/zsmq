package com.github.dhslrl321.zsmq.config;

import com.github.dhslrl321.zsmq.core.queue.ZolaQueueContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZolaBeanConfig {
    @Bean
    public ZolaQueueContainer zolaQueueDispatcher() {
        return new ZolaQueueContainer();
    }
}
