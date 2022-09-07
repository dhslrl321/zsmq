package com.github.dhslrl321.zsmq.listener;

import com.github.dhslrl321.zsmq.util.Pair;

public interface MessageHandlerExecutor {
    void execute(Pair<MessageHandlerTarget, ListeningInformation> pair);
}
