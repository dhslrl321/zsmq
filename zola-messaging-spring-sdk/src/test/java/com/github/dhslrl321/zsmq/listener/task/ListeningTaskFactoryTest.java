package com.github.dhslrl321.zsmq.listener.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.listener.SpringBeanMessageListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListeningTaskFactoryTest {

    public static final Object ANY = null;
    public static final Method ANY_METHOD = null;
    public static final SpringBeanMessageListener LISTENER = SpringBeanMessageListener.of(ANY, ANY_METHOD);
    public static final String ANY_STRING = "";
    public static final ListeningInformation LISTENING_INFORMATION = ListeningInformation.of(ANY_STRING, ANY_STRING);

    ListeningTaskFactory sut;

    @BeforeEach
    void setUp() {
        sut = new ListeningTaskFactory();
    }

    @Test
    void can_create_by_pair() {
        Pair<MessageListener, ListeningInformation> pair = getDummyListeningPair();

        ListeningTask actual = sut.createBy(pair);

        assertThat(actual.getListeningInformation()).isEqualTo(LISTENING_INFORMATION);
    }

    @Test
    void can_create_by_pair_list() {
        List<Pair<MessageListener, ListeningInformation>> pairs = getDummyListeningPairs();

        List<ListeningTask> actual = sut.createBy(pairs);

        assertThat(actual.size()).isEqualTo(2);
    }

    private List<Pair<MessageListener, ListeningInformation>> getDummyListeningPairs() {
        return List.of(getDummyListeningPair(), getDummyListeningPair());
    }

    private Pair<MessageListener, ListeningInformation> getDummyListeningPair() {
        return Pair.of(LISTENER, LISTENING_INFORMATION);
    }
}