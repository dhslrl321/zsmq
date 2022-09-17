package com.github.dhslrl321.zsmq.converter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.SharedFixture;
import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonMessageConverterTest {
    JsonMessageConverter sut = new JsonMessageConverter();

    @Test
    void only_object_type() {
        assertThat(sut.isSupport(new SharedFixture.Foo("hello"))).isTrue();
    }

    @Test
    void if_not_object() {
        assertThat(sut.isSupport("hello")).isFalse();
    }

    @Test
    void can_convert() {
        ZolaMessage actual = sut.toMessage("ANY_QUEUE", new SharedFixture.Foo("hello"));

        assertThat(actual.getHeader().getMediaType()).isEqualTo(MediaTypes.JSON);
        assertThat(actual.getPayload().getValue()).isEqualTo("{\"bar\":\"hello\"}");

        System.out.println("actual = " + actual);
    }

    @Test
    @DisplayName("payload -> message")
    void convert_to_json() {
        assertThat(sut.isSupport("bar")).isFalse();
        assertThat(sut.isSupport(new SharedFixture.Foo("bar"))).isTrue();
    }

    @Test
    @DisplayName("message -> payload")
    void toString_Test() {
        ZolaMessage message = sut.toMessage("SOME_QUEUE", new SharedFixture.Foo("hello"));
        String actual = sut.fromMessage(message);

        assertThat(actual).isEqualTo("{\"bar\":\"hello\"}");
    }
}