package com.wonit.zola;

import com.wonit.zola.queue.ZolaQueueDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZolaBeanConfig {
    @Bean
    public ZolaQueueDispatcher zolaQueueDispatcher() {
        return new ZolaQueueDispatcher();
    }
}
