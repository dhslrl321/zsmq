package com.github.dhslrl321.zsmq;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.queue.ZolaQueueContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BeanRegisterTest {

    @Autowired
    ApplicationContext context;

    @Test
    void name() {
        ZolaQueueContainer actual = context.getBean("zolaQueueDispatcher", ZolaQueueContainer.class);

        assertThat(actual).isNotNull();
    }
}