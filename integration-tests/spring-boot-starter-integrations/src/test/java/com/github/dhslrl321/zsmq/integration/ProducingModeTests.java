package com.github.dhslrl321.zsmq.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.listener.ZolaMessageListeningProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "producing")
class ProducingModeTests {

    @Autowired
    ZolaClientConfig zolaClientConfig;

    @Autowired
    ZolaQueueMessageTemplate zolaQueueMessageTemplate;

    @Autowired
    ZolaMessageListeningProcessor processor;


    @Value("${zsmq.listening}")
    private boolean LISTENING_FROM_PROPS;

    @Test
    void bean_creation_test_ListeningProcessor() {
        assertThatSpringBean(processor);

        assertThat(processor.isListening()).isEqualTo(LISTENING_FROM_PROPS);
    }

    private void assertThatSpringBean(Object bean) {
        assertThat(bean).isNotNull();
    }
}
