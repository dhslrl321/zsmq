package com.github.dhslrl321.zsmq.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompositeMessageConverterTest {

    CompositeMessageConverter sut = new CompositeMessageConverter();

    @Test
    void name() {
        assertThatThrownBy(() -> sut.isSupport("any")).isInstanceOf(UnsupportedOperationException.class);
    }

    @BeforeEach
    void setUp() {
        sut.register(new StringMessageConverter());
        sut.register(new JsonMessageConverter());
    }
}