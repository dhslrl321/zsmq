package com.github.dhslrl321.zsmq.converter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class StringMessageConverterTest {

    StringMessageConverter sut = new StringMessageConverter();

    static class Foo {
        String bar;
    }

    @Test
    void ifStringType() {
        boolean actual = sut.isSupport("");

        assertThat(actual).isTrue();
    }

    @Test
    void ifNotStringType() {
        boolean actual = sut.isSupport(new Foo());

        assertThat(actual).isFalse();
    }

    @Test
    void convert_to_string() {
        ZolaMessage actual = sut.toMessage("any name", "any payload");

        assertThat(actual.getPayload().getValue()).isEqualTo("any payload");
    }

    @Test
    void convert_from_message() {
        ZolaMessage message = ZolaMessage.of(ZolaHeader.of(QueueName.of("any queue name"), LocalDateTime.now(), MediaTypes.TEXT),
                ZolaPayload.of("any payload"));

        String actual = sut.fromMessage(message);

        assertThat(actual).isEqualTo("any payload");
    }
}