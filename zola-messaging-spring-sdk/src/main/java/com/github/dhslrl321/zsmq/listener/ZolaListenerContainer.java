package com.github.dhslrl321.zsmq.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZolaListenerContainer {
    private final ZolaBeanFinder zolaBeanFinder;
    private final ZolaMessageHandlerDetector zolaMessageHandlerDetector;
    private final MessageHandlerExecutor handlerExecutor;

}
