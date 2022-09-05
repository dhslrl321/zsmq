package com.github.dhslrl321.zsmq.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.github.dhslrl321.zsmq.message.MediaTypes;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

class JsonMessageConverterTest {
    JsonMessageConverter sut = new JsonMessageConverter();

    @AllArgsConstructor
    static class Foo {
        String bar;
    }

    @Test
    void only_object_type() {
        assertThat(sut.isSupport(new Foo("hello"))).isTrue();
    }

    @Test
    void if_not_object() {
        assertThat(sut.isSupport("hello")).isFalse();
    }

    @Test
    void can_convert() {
        ZolaMessage actual = sut.toMessage("ANY_QUEUE", new Foo("hello"));

        assertThat(actual.getZolaHeader().getMediaTypes()).isEqualTo(MediaTypes.JSON);
        assertThat(actual.getZolaPayload().getValue()).isEqualTo("{\"bar\":\"hello\"}");
    }
}