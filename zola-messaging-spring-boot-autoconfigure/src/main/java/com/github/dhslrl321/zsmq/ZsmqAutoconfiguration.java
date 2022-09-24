package com.github.dhslrl321.zsmq;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.detector.MessageListenerDetector;
import com.github.dhslrl321.zsmq.detector.SpringBeanMessageListenerDetector;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.listener.ZolaMessageListeningProcessor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import com.github.dhslrl321.zsmq.listener.task.ThreadPoolListeningExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZsmqProperty.class)
@RequiredArgsConstructor
public class ZsmqAutoconfiguration {
    private final ZsmqProperty property;

    @Bean
    @ConditionalOnMissingBean
    public ZolaClientConfig zolaClientConfig() {
        return new ZolaClientConfig(property.getUrl());
    }

    @Bean
    @ConditionalOnMissingBean
    public ZolaQueueMessageTemplate zolaQueueMessageTemplate(ZolaClientConfig zolaClientConfig) {
        return new ZolaQueueMessageTemplate(zolaClientConfig, new ZolaHttpClient());
    }

    @Bean
    @ConditionalOnMissingBean
    public ZolaMessageListeningProcessor container(ApplicationContext applicationContext,
                                                   ZolaClientConfig zolaClientConfig) {
        MessageListenerDetector detector = new SpringBeanMessageListenerDetector(
                applicationContext.getBeansWithAnnotation(ZolaConsumer.class), zolaClientConfig);
        ListeningTaskExecutor taskExecutor = new ThreadPoolListeningExecutor();
        ListeningTaskFactory taskFactory = new ListeningTaskFactory();
        return new ZolaMessageListeningProcessor(detector, taskFactory, taskExecutor);
    }
}

