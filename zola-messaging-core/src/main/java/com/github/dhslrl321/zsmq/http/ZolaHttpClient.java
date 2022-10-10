package com.github.dhslrl321.zsmq.http;

import com.github.dhslrl321.zsmq.commons.ZolaJsonSerializer;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
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
    private static final int NO_CONTENT = 204;
    private static final int NOT_FOUND = 404;
    private static final int BAD_REQUEST = 400;

    private final OkHttpClient http = new OkHttpClient();

    public boolean requestPush(String baseUrl, ZolaMessage message) {
        Request request = new Builder()
                .url(baseUrl + "/api/messages")
                .post(RequestBody.create(ZolaJsonSerializer.getInstance().serialize(message), JSON))
                .build();
        Call call = http.newCall(request);
        try (Response response = call.execute()){
            validateResponse(response.code(), message.getQueueNameValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZolaServerConnectionFailedException(
                    "unexpected exception occurred while communicate with zola messaging server");
        }
    }

    public Optional<ZolaMessage> requestPeek(String baseUrl, String queueName) {
        Request request = new Builder()
                .url(baseUrl + "/api/queues/" + queueName)
                .get()
                .build();
        Call call = http.newCall(request);
        try (Response response = call.execute()){
            validateResponse(response.code(), queueName);
            if (NO_CONTENT == response.code()) {
                return Optional.empty();
            }
            return Optional.of(
                    ZolaJsonSerializer.getInstance().deserialize(Objects.requireNonNull(response.body()).string(), ZolaMessage.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZolaServerConnectionFailedException(
                    "unexpected exception occurred while communicate with zola messaging server");
        }
    }

    public boolean acknowledgement(String baseUrl, String queueName) {
        Request request = new Builder()
                .url(baseUrl + "/api/queues/" + queueName + "/acknowledge")
                .delete()
                .build();
        Call call = http.newCall(request);
        try (Response response = call.execute()){
            validateResponse(response.code(), queueName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZolaServerConnectionFailedException(
                    "unexpected exception occurred while communicate with zola messaging server");
        }
    }

    private void validateResponse(int code, String message) {
        badRequest(code);
        notFound(code, message);
    }

    private void notFound(int code, String queueName) {
        if (NOT_FOUND == code) {
            throw new ZolaServerCommunicationException(
                    String.format("zola messaging server push failed! Queue Name [%s] was not found!", queueName));
        }
    }

    private void badRequest(int code) {
        if (BAD_REQUEST == code) {
            throw new ZolaServerCommunicationException("zola messaging server push failed! with invalid request body data");
        }
    }
}
