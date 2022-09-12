package com.github.dhslrl321.zsmq;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

public class SharedFixture {
    /**
     * this is sharedFixture that commonly use.
     * this is immutable and use common (any) case
     * 절대 변경되어서는 안됩니다
     */

    public static final FooBean ANY_FOO_BEAN = new FooBean("jang", 26);
    public static final BarBean ANY_BAR_BEAN = new BarBean("seoul", 123);
    public static final Map<String, Object> ANY_BEANS = initBeans();

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
