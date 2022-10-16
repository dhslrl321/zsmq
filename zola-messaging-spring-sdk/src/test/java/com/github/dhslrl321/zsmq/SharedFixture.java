package com.github.dhslrl321.zsmq;

import com.github.dhslrl321.zsmq.commons.ZolaJsonSerializer;
import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class SharedFixture {
    /**
     * this is sharedFixture that commonly use.
     * this is immutable and use common (any) case
     * 절대 변경되어서는 안됩니다
     */

    public static final FooBean ANY_FOO_BEAN = new FooBean("jang", 26);
    public static final BarBean ANY_BAR_BEAN = new BarBean("seoul", 123);
    public static final Map<String, Object> ANY_BEANS = initBeans();
    public static final QueueName ANY_QUEUE_NAME = QueueName.of("ANY_QUEUE_NAME");
    public static final ZolaMessage ANY_STRING_MESSAGE = ZolaMessage.of(ZolaHeader.of(ANY_QUEUE_NAME, LocalDateTime.now(),
            MediaTypes.TEXT), ZolaPayload.of("hello"));

    public static final ZolaMessage ANY_JSON_MESSAGE = ZolaMessage.of(ZolaHeader.of(ANY_QUEUE_NAME, LocalDateTime.now(),
            MediaTypes.JSON), ZolaPayload.of(ZolaJsonSerializer.getInstance().serialize(ANY_FOO_BEAN)));

    private static HashMap<String, Object> initBeans() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("fooBean", ANY_FOO_BEAN);
        map.put("barBean", ANY_BAR_BEAN);

        return map;
    }

    @Data
    @AllArgsConstructor
    public static class FooBean {
        String name;
        int age;
    }

    @Data
    @AllArgsConstructor
    public static class BarBean {
        String address;
        int code;
    }
}
