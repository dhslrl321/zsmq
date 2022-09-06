package com.github.dhslrl321.zsmq;

import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.listener.MessageHandlerExecutor;
import com.github.dhslrl321.zsmq.listener.ZolaBeanFinder;
import com.github.dhslrl321.zsmq.listener.ZolaMessageHandlerDetector;
import com.github.dhslrl321.zsmq.listener.ZolaListenerContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZolaListenerContainerTest {

    ZolaListenerContainer sut;

    ZolaBeanFinder zolaBeanFinder = mock(ZolaBeanFinder.class);
    ZolaMessageHandlerDetector zolaMessageHandlerDetector = mock(ZolaMessageHandlerDetector.class);
    MessageHandlerExecutor handlerExecutor = mock(MessageHandlerExecutor.class);

    @BeforeEach
    void setUp() {
        sut = new ZolaListenerContainer(zolaBeanFinder, zolaMessageHandlerDetector, handlerExecutor);
    }

    @Test
    void name() {

    }
}