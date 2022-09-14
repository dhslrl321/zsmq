package com.github.dhslrl321.zsmq.converter;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;

public interface Supportable {

    boolean isSupport(Object payload);

    boolean isSupport(ZolaMessage message);
}
