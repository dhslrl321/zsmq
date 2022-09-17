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
        ZolaHeader zolaHeader = ZolaHeader.of(QueueName.of(headerModel.getQueueName().getValue()), headerModel.getTimestamp(),
                MediaTypes.valueOf(headerModel.getMediaType()));
        ZolaPayload zolaPayload = ZolaPayload.of(model.getPayload().getValue());
        return ZolaMessage.of(zolaHeader, zolaPayload);
    }

    public static MessageModel convert(ZolaMessage zolaMessage) {
        HeaderModel headerModel = new HeaderModel(new QueueNameModel(zolaMessage.getHeader().getQueueName().getValue()),
                zolaMessage.getHeader().getTimestamp(), zolaMessage.getMediaType().name());
        return new MessageModel(headerModel, new PayloadModel(zolaMessage.getPayload().getValue()));
    }
}
