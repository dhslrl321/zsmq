package com.wonit.deliveryservice;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.detector.ListenerBeanFinder;
import com.github.dhslrl321.zsmq.detector.SpringBeanMessageListenerDetector;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.listener.ZolaListenerContainer;
import com.github.dhslrl321.zsmq.listener.task.ThreadPoolListeningExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZsmqConfig {
    @Bean
    public ZolaClientConfig zolaClientConfig() {
        return new ZolaClientConfig("http://localhost:8291");
    }

    @Bean
    public ZolaQueueMessageTemplate zolaQueueMessageTemplate(ZolaClientConfig zolaClientConfig) {
        return new ZolaQueueMessageTemplate(zolaClientConfig, new ZolaHttpClient());
    }

    @Bean
    public ZolaListenerContainer zolaListenerContainer(ApplicationContext applicationContext,
                                                       ZolaClientConfig zolaClientConfig) {
        SpringBeanMessageListenerDetector detector = new SpringBeanMessageListenerDetector(
                new ListenerBeanFinder(applicationContext), zolaClientConfig);
        ThreadPoolListeningExecutor executor = new ThreadPoolListeningExecutor();

        ZolaListenerContainer container = new ZolaListenerContainer(detector, executor);

        ListenerThread thread = new ListenerThread(container);
        thread.start();

        return container;
    }
}

@RequiredArgsConstructor
class ListenerThread extends Thread {

    private final ZolaListenerContainer container;

    @Override
    public void run() {
        container.listenAll();
    }
}
