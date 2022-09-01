package com.wonit.zola;

import static org.assertj.core.api.Assertions.assertThat;

import com.wonit.zola.queue.ZolaQueueDispatcher;
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
        ZolaQueueDispatcher actual = context.getBean("zolaQueueDispatcher", ZolaQueueDispatcher.class);

        assertThat(actual).isNotNull();
    }
}