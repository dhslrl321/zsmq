package com.github.dhslrl321.zsmq.listener.strategy;

import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.http.ZolaHttpClient;
import com.github.dhslrl321.zsmq.listener.ListeningInformation;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpPollingListeningStrategy implements ListeningStrategy {

    private ZolaHttpClient httpClient = new ZolaHttpClient();

    @Override
    public ZolaMessage peek(ListeningInformation information) {
        Optional<ZolaMessage> optionalMessage = httpClient.requestPeek(information.getServer(), information.getQueueName());

        if (optionalMessage.isEmpty()) {
            return null;
        }

        return optionalMessage.get();
    }

    @Override
    public boolean acknowledgement(ListeningInformation information) {
        return httpClient.acknowledgement(information.getServer(), information.getQueueName());
    }

    protected void setHttpClient(ZolaHttpClient fakeHttpClient) {
        httpClient = fakeHttpClient;
    }
}
