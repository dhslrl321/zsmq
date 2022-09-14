package com.github.dhslrl321.zsmq;

import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ZolaQueueMessageTemplate.class)
@RequiredArgsConstructor
public class ZsmqAutoconfiguration {
    private final ZsmqProperty property;

    @Bean
    @ConditionalOnMissingBean
    public ZolaQueueMessageTemplate zolaQueueMessageTemplate() {
        return new ZolaQueueMessageTemplate(new ZolaClientConfig(property.getServer()), new ZolaHttpClient());
    }
}
