package com.github.dhslrl321.zsmq.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.SharedFixture;
import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class ZolaBeanFinderTest {

    ZolaBeanFinder sut;

    ApplicationContext context = mock(ApplicationContext.class);

    @BeforeEach
    void before() {
        sut = new ZolaBeanFinder();
    }

    @Test
    void name2() {
        given(context.getBeansWithAnnotation(ZolaConsumer.class)).willReturn(SharedFixture.ANY_BEANS);

        Map<String, Object> actual = sut.getZolaBeans(context);

        assertThat(actual.get("fooBean")).isInstanceOf(SharedFixture.FooBean.class);
    }

    @Test
    void name3() {
        given(context.getBeansWithAnnotation(ZolaConsumer.class)).willReturn(SharedFixture.ANY_BEANS);

        Map<String, Object> actual = sut.getZolaBeans(context);

        assertThat(actual.get("barBean")).isInstanceOf(SharedFixture.BarBean.class);
    }

    @Test
    void name4() {
        given(context.getBeansWithAnnotation(ZolaConsumer.class)).willReturn(SharedFixture.ANY_BEANS);

        Map<String, Object> actual = sut.getZolaBeans(context);

        assertThat(actual.get("bazBean")).isNull();
    }
}