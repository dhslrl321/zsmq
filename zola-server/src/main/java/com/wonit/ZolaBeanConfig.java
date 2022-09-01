package com.wonit;

import com.wonit.queue.ZolaQueueDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZolaBeanConfig {
    @Bean
    public ZolaQueueDispatcher zolaQueueDispatcher() {
        return new ZolaQueueDispatcher();
    }
}
