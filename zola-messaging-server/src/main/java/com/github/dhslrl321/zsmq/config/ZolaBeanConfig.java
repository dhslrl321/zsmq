package com.github.dhslrl321.zsmq.config;

import com.github.dhslrl321.zsmq.queue.ZolaQueueDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZolaBeanConfig {
    @Bean
    public ZolaQueueDispatcher zolaQueueDispatcher() {
        return new ZolaQueueDispatcher();
    }
}
