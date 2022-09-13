package com.github.dhslrl321.zsmq.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.zsmq.listener.task.ListeningTask;
import com.github.dhslrl321.zsmq.listener.task.ListeningTaskFactory;
import com.github.dhslrl321.zsmq.commons.Pair;
import org.junit.jupiter.api.Test;

class ListeningTaskFactoryTest {

    ListeningTaskFactory sut = new ListeningTaskFactory();
    MessageListener messageListener = mock(MessageListener.class);
    ListeningInformation listeningInformation = ListeningInformation.of(null);

    Pair<MessageListener, ListeningInformation> pair = Pair.of(messageListener, listeningInformation);

    @Test
    void name() {
        ListeningTask actual = sut.createBy(pair);

        assertThat(actual).isNotNull();
    }
}