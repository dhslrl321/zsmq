package com.github.dhslrl321.zsmq.controller.model;

import com.github.dhslrl321.zsmq.controller.model.MessageModel.HeaderModel;
import com.github.dhslrl321.zsmq.message.ZolaHeader;
import com.github.dhslrl321.zsmq.message.ZolaMessage;
import com.github.dhslrl321.zsmq.message.ZolaPayload;
import com.github.dhslrl321.zsmq.queue.QueueName;

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
                zolaMessage.getZolaHeader().getTimestamp());
        return new MessageModel(headerModel, zolaMessage.getZolaPayload().getValue());
    }
}
