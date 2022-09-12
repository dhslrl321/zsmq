package com.example.example;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.detector.ListenerBeanFinder;
import com.github.dhslrl321.zsmq.detector.SpringBeanMessageListenerDetector;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.listener.ZolaListenerContainer;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import com.github.dhslrl321.zsmq.listener.task.ThreadPoolListeningExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ZolaQueueMessageTemplate zolaQueueMessageTemplate() {
        ZolaHttpClient client = new ZolaHttpClient();
        ZolaClientConfig config = new ZolaClientConfig("http://localhost:8291");
        return new ZolaQueueMessageTemplate(config, client);
    }

    @Bean
    public ZolaListenerContainer container(ApplicationContext context) {
        ListenerBeanFinder finder = new ListenerBeanFinder(context);
        SpringBeanMessageListenerDetector detector = new SpringBeanMessageListenerDetector(finder);
        ThreadPoolListeningExecutor executor = new ThreadPoolListeningExecutor();
        return new ZolaListenerContainer(detector, new ListeningTaskFactory(), executor);
    }

    @Bean
    public String listen(ZolaListenerContainer container) {
        container.listen();
        return "";
    }
}
