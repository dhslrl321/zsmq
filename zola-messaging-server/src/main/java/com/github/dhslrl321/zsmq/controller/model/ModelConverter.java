package com.github.dhslrl321.zsmq.controller.model;

import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;

public class ModelConverter {
    public static ZolaMessage convert(MessageModel model) {
        // TODO Singleton
        HeaderModel headerModel = model.getHeader();
        ZolaHeader zolaHeader = ZolaHeader.of(QueueName.of(headerModel.getQueueName()), headerModel.getTimestamp(),
                MediaTypes.valueOf(headerModel.getMediaType()));
        ZolaPayload zolaPayload = ZolaPayload.of(model.getPayload());
        return ZolaMessage.of(zolaHeader, zolaPayload);
    }

    public static MessageModel convert(ZolaMessage zolaMessage) {
        HeaderModel headerModel = new HeaderModel(zolaMessage.getZolaHeader().getQueueName().getValue(),
                zolaMessage.getZolaHeader().getTimestamp(), zolaMessage.getMediaType().name());
        return new MessageModel(headerModel, zolaMessage.getZolaPayload().getValue());
    }
}
