package com.github.dhslrl321.zsmq.integration;

import com.github.dhslrl321.zsmq.client.ZolaClientConfig;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeforeTestConfiguration {

    public static final String TEST_QUEUE_NAME = "TEST-QUEUE-NAME";

    @Autowired
    ZolaClientConfig zolaClientConfig;
    RestTemplate rest = new RestTemplate();

    @PostConstruct
    public void makeQueueBeforeConstruct() {
        Map<String, String> map = new HashMap<>();
        map.put("name", TEST_QUEUE_NAME);

        rest.postForObject(zolaClientConfig.getServerBaseUrl() + "/api/queues", map, Map.class);
    }

    @PreDestroy
    public void cleanUpQueue() {
        rest.delete(zolaClientConfig.getServerBaseUrl() + "/api/queues/" + TEST_QUEUE_NAME);
    }
}
