package com.github.dhslrl321.zsmq.commons;

import com.github.dhslrl321.zsmq.SharedFixture;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

class ZolaJsonSerializerTest {

    @Data
    @AllArgsConstructor
    static class Foo {
        String name;
        Bar bar;
    }

    @Data
    @AllArgsConstructor
    static class Bar {
        String address;

    }

    @Test
    void name() {
        //String serialized = Serializer.serialize(SharedFixture.ANY_JSON_MESSAGE);
        Gson gson = new Gson();
        String serialized = gson.toJson(SharedFixture.ANY_STRING_MESSAGE);
        System.out.println("serialized = " + serialized);
    }
}