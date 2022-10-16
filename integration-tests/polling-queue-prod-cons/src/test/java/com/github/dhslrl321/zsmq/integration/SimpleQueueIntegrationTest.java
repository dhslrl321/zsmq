package com.github.dhslrl321.zsmq.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleQueueIntegrationTest {

    public static final String TEST_QUEUE_NAME = "TEST-QUEUE-NAME";
    public static final String PUBLISH_MESSAGE = "test message publication";
    /**
     * if you want to run this test, you should run zola-message-server in local. Later, we will provide testcontainers
     * for regression test
     */

    @Autowired
    ZolaQueueMessageTemplate template;

    @Autowired
    ZolaClientConfig zolaClientConfig;

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    /**
     * @see BeforeTestConfiguration, There are few configurations, such as making queue or destroying queue when test is
     * done
     *
     * @see FooZolaMessageListener, It is simple listening setting
     */
    @Test
    void producing_and_consuming() {
        template.convertAndSend(TEST_QUEUE_NAME, PUBLISH_MESSAGE);

        assertThatInConsole(PUBLISH_MESSAGE);
    }

    private void assertThatInConsole(String message) {
        assertThat(message).isEqualTo(outputStream.toString());
    }
}
