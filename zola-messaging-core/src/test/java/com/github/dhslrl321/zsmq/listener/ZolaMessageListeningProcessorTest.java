package com.github.dhslrl321.zsmq.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.detector.MessageListenerDetector;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskExecutor;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ZolaMessageListeningProcessorTest {

    @InjectMocks
    ZolaMessageListeningProcessor sut;

    @Mock
    MessageListenerDetector detector;
    @Mock
    ListeningTaskFactory factory;
    @Mock
    ListeningTaskExecutor executor;

    @Test
    void when_doProcess_is_called() {
        assertThat(sut.isListening()).isFalse();

        sut.doProcess();

        assertThat(sut.isListening()).isTrue();
    }
}