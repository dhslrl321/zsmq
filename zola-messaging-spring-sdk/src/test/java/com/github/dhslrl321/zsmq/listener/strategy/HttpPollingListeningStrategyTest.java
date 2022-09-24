package com.github.dhslrl321.zsmq.listener.strategy;

import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import org.junit.jupiter.api.Test;

class HttpPollingListeningStrategyTest {

    private static final String ANY_QUEUE_NAME = "any_queue_name";
    private static final String ANY_SERVER = "any_server";

    HttpPollingListeningStrategy sut;

    @Test
    void name() {
        ZolaHttpClient client = mock(ZolaHttpClient.class);

        sut = new HttpPollingListeningStrategy();
        sut.peek(ANY_SERVER, ANY_QUEUE_NAME);
    }
}