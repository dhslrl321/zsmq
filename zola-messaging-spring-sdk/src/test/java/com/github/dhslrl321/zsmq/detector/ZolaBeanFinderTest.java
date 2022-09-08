package com.github.dhslrl321.zsmq.detector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.SharedFixture;
import com.github.dhslrl321.zsmq.annotation.ZolaConsumer;
import com.github.dhslrl321.zsmq.detector.ListenerBeanFinder;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class ZolaBeanFinderTest {

    ListenerBeanFinder sut;

    ApplicationContext context = mock(ApplicationContext.class);

    @BeforeEach
    void before() {
        sut = new ListenerBeanFinder(context);
    }

    @Test
    void find() {
        given(context.getBeansWithAnnotation(ZolaConsumer.class)).willReturn(SharedFixture.ANY_BEANS);

        Map<String, Object> actual = sut.getZolaBeans();

        assertThat(actual.get("fooBean")).isInstanceOf(SharedFixture.FooBean.class);
    }

    @Test
    void find_another() {
        given(context.getBeansWithAnnotation(ZolaConsumer.class)).willReturn(SharedFixture.ANY_BEANS);

        Map<String, Object> actual = sut.getZolaBeans();

        assertThat(actual.get("barBean")).isInstanceOf(SharedFixture.BarBean.class);
    }

    @Test
    void find_empty() {
        given(context.getBeansWithAnnotation(ZolaConsumer.class)).willReturn(SharedFixture.ANY_BEANS);

        Map<String, Object> actual = sut.getZolaBeans();

        assertThat(actual.get("bazBean")).isNull();
    }
}