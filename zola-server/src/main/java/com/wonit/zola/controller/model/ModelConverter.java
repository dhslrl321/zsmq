package com.wonit.zola.controller.model;

import com.wonit.zola.controller.model.MessageModel.HeaderModel;
import com.wonit.zola.message.ZolaHeader;
import com.wonit.zola.message.ZolaMessage;
import com.wonit.zola.message.ZolaPayload;
import com.wonit.zola.queue.QueueName;

public class ModelConverter {
    public static ZolaMessage convert(MessageModel model) {
        // TODO Singleton
        HeaderModel headerModel = model.getHeader();
        ZolaHeader zolaHeader = ZolaHeader.of(QueueName.of(headerModel.getQueueName()), headerModel.getPublishedAt());
        ZolaPayload zolaPayload = ZolaPayload.of(model.getPayload());
        return ZolaMessage.of(zolaHeader, zolaPayload);
    }

    public static MessageModel convert(ZolaMessage zolaMessage) {
        HeaderModel headerModel = new HeaderModel(zolaMessage.getZolaHeader().getQueueName().getValue(),
                zolaMessage.getZolaHeader().getPublishedAt());
        return new MessageModel(headerModel, zolaMessage.getZolaPayload().getValue());
    }
}
