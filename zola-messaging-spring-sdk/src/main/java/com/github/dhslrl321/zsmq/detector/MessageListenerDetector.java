package com.github.dhslrl321.zsmq.detector;

import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import com.github.dhslrl321.zsmq.util.Pair;
import java.util.List;

public interface MessageListenerDetector {
    List<Pair<MessageListener, ListeningInformation>> detect();
}
