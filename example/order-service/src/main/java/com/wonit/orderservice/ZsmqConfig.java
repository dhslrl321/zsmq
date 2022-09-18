package com.wonit.orderservice;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZsmqConfig {
    @Bean
    public ZolaClientConfig config() {
        return new ZolaClientConfig("http://localhost:8291");
    }

    @Bean
    public ZolaQueueMessageTemplate template(ZolaClientConfig config) {
        return new ZolaQueueMessageTemplate(config, new ZolaHttpClient());
    }
}
