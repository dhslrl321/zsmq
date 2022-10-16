package com.github.dhslrl321.zsmq.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.listener.ZolaMessageListeningProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "consuming")
class BeanAutoCreationTests {

    @Autowired
    ZolaClientConfig zolaClientConfig;

    @Autowired
    ZolaQueueMessageTemplate zolaQueueMessageTemplate;

    @Autowired
    ZolaMessageListeningProcessor processor;

    @Value("${zsmq.url}")
    private String URL_FROM_PROPS;

    @Test
    void bean_creation_test_ZolaClientConfig() {
        assertThatSpringBean(zolaClientConfig);
        assertThat(zolaClientConfig.getServerBaseUrl()).isEqualTo(URL_FROM_PROPS);
    }

    @Test
    void bean_creation_test_ZolaQueueMessageTemplate() {
        assertThatSpringBean(zolaQueueMessageTemplate);
    }

    @Test
    void bean_creation_test_ListeningProcessor() {
        assertThatSpringBean(processor);
        assertThat(processor.isListening()).isFalse();
    }

    private void assertThatSpringBean(Object bean) {
        assertThat(bean).isNotNull();
    }
}
