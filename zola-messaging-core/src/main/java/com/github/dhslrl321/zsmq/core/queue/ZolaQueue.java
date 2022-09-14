package com.github.dhslrl321.zsmq.core.queue;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;

public interface ZolaQueue extends Describable {
    ZolaMessage peek();

    ZolaMessage pop();

    void push(ZolaMessage zolaMessage);
}
