package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;

public interface ListeningStrategy {
    ZolaMessage peek(ListeningInformation information);

    boolean acknowledgement(ListeningInformation information);
}
