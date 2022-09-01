package com.wonit.zola.controller.model;

import com.wonit.zola.controller.model.MessageModel.HeaderModel;
import com.wonit.zola.message.Header;
import com.wonit.zola.message.Message;
import com.wonit.zola.message.Payload;
import com.wonit.zola.queue.value.QueueName;

public class ModelConverter {
    public static Message convert(MessageModel model) {
        // TODO Singleton
        HeaderModel headerModel = model.getHeader();
        Header header = Header.of(QueueName.of(headerModel.getQueueName()), headerModel.getPublishedAt());
        Payload payload = Payload.of(model.getPayload());
        return Message.of(header, payload);
    }

    public static MessageModel convert(Message message) {
        HeaderModel headerModel = new HeaderModel(message.getHeader().getQueueName().getValue(),
                message.getHeader().getPublishedAt());
        return new MessageModel(headerModel, message.getPayload().getValue());
    }
}
