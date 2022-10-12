package com.github.dhslrl321.zsmq.listener.task;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.SpringBeanMessageListener;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PollingQueueListeningTaskFactoryTest {

    private static final SpringBeanMessageListener LISTENER = SpringBeanMessageListener.of(null, null);
    private static final ListeningInformation LISTENING_INFORMATION = ListeningInformation.of("", "",
            DeletionPolicy.ALWAYS);

    PollingQueueListeningTaskFactory sut;

    @BeforeEach
    void setUp() {
        sut = new PollingQueueListeningTaskFactory();
    }

    @Test
    void can_create_by_pair() {
        ListeningTask actual = sut.createBy(singlePair());

        assertThat(actual.getListeningPair().getRight()).isEqualTo(LISTENING_INFORMATION);
    }

    @Test
    void can_create_by_pair_list() {
        List<ListeningTask> actual = sut.createBy(multiplePair());

        assertThat(actual.size()).isEqualTo(2);
    }

    private List<Pair<MessageListener, ListeningInformation>> multiplePair() {
        return List.of(singlePair(), singlePair());
    }

    private Pair<MessageListener, ListeningInformation> singlePair() {
        return Pair.of(LISTENER, LISTENING_INFORMATION);
    }
}