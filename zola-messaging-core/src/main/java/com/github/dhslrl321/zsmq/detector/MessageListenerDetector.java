package com.github.dhslrl321.zsmq.detector;

import com.github.dhslrl321.zsmq.commons.Pair;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import com.github.dhslrl321.zsmq.listener.MessageListener;
import java.util.List;
import java.util.Map;

public interface MessageListenerDetector {
    List<Pair<MessageListener, ListeningInformation>> detect();
}
