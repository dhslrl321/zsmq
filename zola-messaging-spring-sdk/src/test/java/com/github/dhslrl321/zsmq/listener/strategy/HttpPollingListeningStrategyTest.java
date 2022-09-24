package com.github.dhslrl321.zsmq.listener.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.SharedFixture;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpPollingListeningStrategyTest {

    HttpPollingListeningStrategy sut;

    ZolaHttpClient mockHttpClient = mock(ZolaHttpClient.class);

    @BeforeEach
    void setUp() {
        sut = new HttpPollingListeningStrategy();
        sut.setHttpClient(mockHttpClient);
    }

    @Test
    void peek_not_empty() {
        given(mockHttpClient.requestPeek("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue()))
                .willReturn(Optional.of(SharedFixture.ANY_JSON_MESSAGE));

        ZolaMessage actual = sut.peek("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue());

        assertThat(actual).isNotNull();
    }

    @Test
    void peek_empty() {
        given(mockHttpClient.requestPeek("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue()))
                .willReturn(Optional.empty());

        ZolaMessage actual = sut.peek("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue());

        assertThat(actual).isNull();
    }

    @Test
    void ack_true() {
        given(mockHttpClient.acknowledgement("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue())).willReturn(true);

        boolean actual = sut.ack("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue());

        assertThat(actual).isTrue();
    }

    @Test
    void ack_false() {
        given(mockHttpClient.acknowledgement("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue())).willReturn(false);

        boolean actual = sut.ack("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue());

        assertThat(actual).isFalse();
    }
}

