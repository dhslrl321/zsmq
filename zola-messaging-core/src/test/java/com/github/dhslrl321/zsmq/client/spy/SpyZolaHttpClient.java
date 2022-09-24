package com.github.dhslrl321.zsmq.client.spy;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.Optional;

public class SpyZolaHttpClient extends ZolaHttpClient {

    public boolean pushCalled = false;

    @Override
    public boolean requestPush(String baseUrl, ZolaMessage message) {
        pushCalled = true;
        return true;
    }

    @Override
    public Optional<ZolaMessage> requestPeek(String baseUrl, String queueName) {
        return super.requestPeek(baseUrl, queueName);
    }

    @Override
    public boolean acknowledgement() {
        return super.acknowledgement();
    }
}
