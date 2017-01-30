package com.ef.persistence;

import javax.persistence.*;
import java.util.List;

/**
 * Created by eboh on 22/01/17.
 */
@Entity
@Table(name = "APP_USER")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Podcast> podcasts;

    public User() {
    }

    public User(String name, List<Podcast> podcasts) {
        this.name = name;
        this.podcasts = podcasts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
