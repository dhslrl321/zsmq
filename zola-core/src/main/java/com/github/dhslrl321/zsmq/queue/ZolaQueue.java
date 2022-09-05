package com.github.dhslrl321.zsmq.queue;

import com.github.dhslrl321.zsmq.message.ZolaMessage;

public interface ZolaQueue extends Describable {
    ZolaMessage peek();

    ZolaMessage pop();

    void push(ZolaMessage zolaMessage);
}
