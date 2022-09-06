package com.github.dhslrl321.zsmq.listener;

public interface MessageHandlerExecutor {
    void execute(Pair<MessageHandlerMethod, ListeningInformation> pair);
}
