package com.github.dhslrl321.zsmq.http;

import com.github.dhslrl321.zsmq.commons.Serializer;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ZolaHttpClient {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final int CREATED = 201;
    private final OkHttpClient http = new OkHttpClient();

    public boolean requestPush(String baseUrl, ZolaMessage message) {
        Request request = new Builder()
                .url(baseUrl + "/api/messages")
                .post(RequestBody.create(Serializer.serialize(message), JSON))
                .build();
        Call call = http.newCall(request);
        try {
            Response execute = call.execute();
            int code = execute.code();
            if (CREATED == code) {
                return true;
            }
            throw new ZolaServerConnectionFailedException("zola messaging server push failed!");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(
                    "unexpected exception occurred while communicate with zola messaging server");
        }
    }

    public Optional<ZolaMessage> requestPeek(String baseUrl, String queueName) {
        Request request = new Builder()
                .url(baseUrl + "/api/queues/" + queueName)
                .get()
                .build();
        Call call = http.newCall(request);
        try {
            Response response = call.execute();
            int code = response.code();
            if (200 == code) {
                ZolaMessage message = Serializer.deserialize(Objects.requireNonNull(response.body()).string(),
                        ZolaMessage.class);
                return Optional.of(message);
            } else if (204 == code) {
                return Optional.empty();
            }
            throw new ZolaServerConnectionFailedException(
                    String.format("zola messaging server message push failed! queueName => [%s], server's code => [%s]",
                            queueName, code));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ZolaServerConnectionFailedException("");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(
                    "unexpected exception occurred while communicate with zola messaging server");
        }
    }

    public boolean acknowledgement() {
        return false;
    }
}
