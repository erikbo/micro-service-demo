package com.ef;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by eboh on 27/01/17.
 */
@ConfigurationProperties
@EnableConfigurationProperties
@Configuration
public class Config {

    private String userServiceUrl;
    private String rssServiceUrl;

    public String getUserServiceUrl() {
        return userServiceUrl;
    }

    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    public String getRssServiceUrl() {
        return rssServiceUrl;
    }

    public void setRssServiceUrl(String rssServiceUrl) {
        this.rssServiceUrl = rssServiceUrl;
    }
}
