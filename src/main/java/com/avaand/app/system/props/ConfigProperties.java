package com.avaand.app.utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

    private String username;
    private String password;

}
