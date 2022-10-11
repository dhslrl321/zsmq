package com.github.dhslrl321.zsmq.rpc;

import static org.junit.jupiter.api.Assertions.fail;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class RpcCallEnqueueTest {

    private static final int SIZE = 1_000_000;


    ZolaQueueMessageTemplate sut;

    long start;
    long end;

    @BeforeEach
    void setUp() {
        sut = new ZolaQueueMessageTemplate(
                new ZolaClientConfig("http://localhost:8291"), new ZolaHttpClient());

        start = System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() {
        end = System.currentTimeMillis();
        ResultReporter.reportToConsole(end, start);
    }

    @Test
    @DisplayName("rpc call 1,000,000 messages")
    void TEST() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @Order(0)
    void cannot_be_triggered_by_automatic_build() {
        fail("not support");
    }
}
