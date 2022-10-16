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
import lombok.extern.slf4j.Slf4j;
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
        ZolaMessageListeningProcessor zolaMessageListeningProcessor = newProcessor(
                newDetector(applicationContext, zolaClientConfig),
                newExecutor(),
                newTaskFactory()
        );
        doProcessIfListening(zolaMessageListeningProcessor);
        return zolaMessageListeningProcessor;
    }

    private void doProcessIfListening(ZolaMessageListeningProcessor zolaMessageListeningProcessor) {
        if (property.isListening()) {
            zolaMessageListeningProcessor.doProcess();
        }
    }

    private ZolaMessageListeningProcessor newProcessor(MessageListenerDetector detector,
                                                                           ListeningTaskExecutor taskExecutor,
                                                                           ListeningTaskFactory taskFactory) {
        return new ZolaMessageListeningProcessor(detector,
                taskFactory, taskExecutor);
    }

    private ListeningTaskFactory newTaskFactory() {
        return new ListeningTaskFactory();
    }

    private ThreadPoolListeningExecutor newExecutor() {
        return new ThreadPoolListeningExecutor();
    }

    private SpringBeanMessageListenerDetector newDetector(ApplicationContext applicationContext,
                                                          ZolaClientConfig zolaClientConfig) {
        return new SpringBeanMessageListenerDetector(
                applicationContext.getBeansWithAnnotation(ZolaConsumer.class), zolaClientConfig);
    }
}

