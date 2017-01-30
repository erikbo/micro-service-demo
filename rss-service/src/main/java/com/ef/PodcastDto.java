package com.ef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by eboh on 25/01/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PodcastDto {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
