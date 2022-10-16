package com.github.dhslrl321.zsmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zsmq")
@Setter
@Getter
public class ZsmqProperty {
    private String url;
    private boolean listening;
}
