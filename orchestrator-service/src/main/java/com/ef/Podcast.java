package com.ef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by eboh on 30/01/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Podcast {
    private String name;
    private String url;

    public Podcast() {
    }

    public Podcast(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
