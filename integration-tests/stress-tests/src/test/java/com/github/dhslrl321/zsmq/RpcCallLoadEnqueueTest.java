package com.github.dhslrl321.zsmq;

import static org.junit.jupiter.api.Assertions.fail;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class RpcCallLoadEnqueueTest {

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
    @DisplayName("vUser(1) sending")
    void vUser1() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(2) sending")
    void vUser2() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(3) sending")
    void vUser3() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(4) sending")
    void vUser4() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(5) sending")
    void vUser5() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(6) sending")
    void vUser6() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(7) sending")
    void vUser7() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(8) sending")
    void vUser8() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("vUser(9) sending")
    void vUser9() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @DisplayName("rvUser(0) sending")
    void vUser10() {
        IntStream.range(0, SIZE)
                .forEach((i) -> sut.convertAndSend("TESTING-QUEUE", "dummy message, " + i));
    }

    @Test
    @Order(0)
    @Disabled
    void cannot_be_triggered_by_automatic_build() {
        fail("not support");
    }
}
