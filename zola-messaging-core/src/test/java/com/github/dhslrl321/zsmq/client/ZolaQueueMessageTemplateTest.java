package com.github.dhslrl321.zsmq.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import com.github.dhslrl321.zsmq.client.ZolaQueueMessageTemplate;
import com.github.dhslrl321.zsmq.client.spy.SpyZolaHttpClient;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ZolaQueueMessageTemplateTest {

    public static final String ANY_STRING = "ANY_STRING";

    ZolaQueueMessageTemplate sut;

    SpyZolaHttpClient spyHttpClient = new SpyZolaHttpClient();

    @BeforeEach
    void setUp() {
        sut = new ZolaQueueMessageTemplate(new ZolaClientConfig(ANY_STRING), spyHttpClient);
    }

    @Test
    void happy_case() {
        ZolaMessage message = ZolaMessage.of(ZolaHeader.of(QueueName.of(ANY_STRING), LocalDateTime.now(), MediaTypes.JSON),
                ZolaPayload.of(ANY_STRING));

        sut.convertAndSend(ANY_STRING, message);

        assertThat(spyHttpClient.pushCalled).isTrue();
    }

    @Test
    void queueName_must_be_notEmpty() {
        assertThatThrownBy(() -> sut.convertAndSend("", ANY_STRING))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void queueName_must_be_notNull() {
        assertThatThrownBy(() -> sut.convertAndSend(null, ANY_STRING))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void payload_must_be_notNull() {
        assertThatThrownBy(() -> sut.convertAndSend(ANY_STRING, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
