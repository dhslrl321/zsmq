package com.wonit.deliveryservice;

import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.detector.SpringBeanMessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.ZolaMessageListeningProcessor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
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
    public ZolaMessageListeningProcessor zolaListenerContainer(ApplicationContext applicationContext,
                                                               ZolaClientConfig zolaClientConfig) {
        SpringBeanMessageListenerDetector detector = new SpringBeanMessageListenerDetector(
                applicationContext.getBeansWithAnnotation(ZolaConsumer.class), zolaClientConfig);
        ThreadPoolListeningExecutor executor = new ThreadPoolListeningExecutor();
        ListeningTaskFactory taskFactory = new ListeningTaskFactory();

        ZolaMessageListeningProcessor container = new ZolaMessageListeningProcessor(detector, taskFactory, executor);
        ListenerThread thread = new ListenerThread(container);
        thread.start();
        return container;
    }
}

@RequiredArgsConstructor
class ListenerThread extends Thread {

    private final ZolaMessageListeningProcessor container;

    @Override
    public void run() {
        container.doProcess();
    }
}
