package com.github.dhslrl321.zsmq.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

class SerializerTest {

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
        Foo foo = new Foo("jang", new Bar("seoul"));
        String serialized = Serializer.serialize(foo);

    }
}