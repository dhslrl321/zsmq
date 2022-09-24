package com.github.dhslrl321.zsmq.listener.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.SharedFixture;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpPollingListeningStrategyTest {

    public static final ListeningInformation LISTENING_INFORMATION = ListeningInformation.of("ANY_SERVER",
            SharedFixture.ANY_QUEUE_NAME.getValue(), DeletionPolicy.ALWAYS);
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

        ZolaMessage actual = sut.peek(LISTENING_INFORMATION);

        assertThat(actual).isNotNull();
    }

    @Test
    void peek_empty() {
        given(mockHttpClient.requestPeek("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue()))
                .willReturn(Optional.empty());

        ZolaMessage actual = sut.peek(LISTENING_INFORMATION);

        assertThat(actual).isNull();
    }

    @Test
    void ack_true() {
        given(mockHttpClient.acknowledgement("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue())).willReturn(true);

        boolean actual = sut.acknowledgement(LISTENING_INFORMATION);

        assertThat(actual).isTrue();
    }

    @Test
    void ack_false() {
        given(mockHttpClient.acknowledgement("ANY_SERVER", SharedFixture.ANY_QUEUE_NAME.getValue())).willReturn(false);

        boolean actual = sut.acknowledgement(LISTENING_INFORMATION);

        assertThat(actual).isFalse();
    }
}

