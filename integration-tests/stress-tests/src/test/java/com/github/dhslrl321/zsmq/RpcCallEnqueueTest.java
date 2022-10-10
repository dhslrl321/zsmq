package com.github.dhslrl321.zsmq;

import static org.junit.jupiter.api.Assertions.fail;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class RpcCallEnqueueTest {

    private static final int SIZE = 1_000_000;
    public static final String TOOK_MS_MESSAGE = "test took (ms) : ";

    ZolaQueueMessageTemplate sut;


    long before;
    long after;

    @BeforeEach
    void setUp() {
        sut = new ZolaQueueMessageTemplate(
                new ZolaClientConfig("http://localhost:8291"), new ZolaHttpClient());

        before = System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() {
        after = System.currentTimeMillis();
        long diff = (after - before);
        System.out.println(TOOK_MS_MESSAGE + diff + " (ms)");
    }

    @Test
    @DisplayName("rpc call 1,000,000 messages")
    void name() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE-", "dummy message, " + i));
    }

    @Test
    @Order(0)
    void cannot_be_triggered_by_automatic_build() {
        fail("not support");
    }
}
