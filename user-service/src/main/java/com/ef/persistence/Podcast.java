package com.ef.persistence;

import javax.persistence.*;

/**
 * Created by eboh on 25/01/17.
 */
@Entity
@Table(name = "APP_PODCAST")
public class Podcast {

    public Podcast() {
    }

    public Podcast(Long id, User user, String name, String url) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "podcasts")
    private User user;

    private String name;
    private String url;

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
