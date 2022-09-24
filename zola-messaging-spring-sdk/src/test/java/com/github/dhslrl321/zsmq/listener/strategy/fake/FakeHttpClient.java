package com.github.dhslrl321.zsmq.listener.strategy.fake;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import java.util.Optional;

public class FakeHttpClient extends ZolaHttpClient {
    @Override
    public boolean requestPush(String baseUrl, ZolaMessage message) {
        return true;
    }

    @Override
    public Optional<ZolaMessage> requestPeek(String baseUrl, String queueName) {
        return Optional.empty();
    }

    @Override
    public boolean acknowledgement(String server, String queueName) {
        return false;
    }
}