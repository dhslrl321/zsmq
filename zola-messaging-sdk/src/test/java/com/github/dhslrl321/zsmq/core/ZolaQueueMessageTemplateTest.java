package com.github.dhslrl321.zsmq.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.github.dhslrl321.zsmq.conn.ZolaHttpClient;
import com.github.dhslrl321.zsmq.message.MediaTypes;
import com.github.dhslrl321.zsmq.message.ZolaHeader;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import com.github.dhslrl321.zsmq.message.ZolaPayload;
import com.github.dhslrl321.zsmq.queue.QueueName;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ZolaQueueMessageTemplateTest {

    ZolaQueueMessageTemplate sut;

    ZolaHttpClient httpClient = mock(ZolaHttpClient.class);

    @BeforeEach
    void setUp() {
        sut = new ZolaQueueMessageTemplate(new ZolaClientConfig("some"), httpClient);
    }

    @Test
    @Disabled("Tests always fail due to problems with LocalDateTime.now()")
    void name() {
        ZolaMessage message = ZolaMessage.of(ZolaHeader.of(QueueName.of("some"), LocalDateTime.now(), MediaTypes.TEXT),
                ZolaPayload.of("hello"));

        sut.convertAndSend("some", "hello");

        verify(httpClient).post("some", message);
    }

}
