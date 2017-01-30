package com.ef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * Created by eboh on 25/01/17.
 */
@RestController
public class UserController {

    private static final String USER_PODCASTS_URL_SUFFIX = "/users/%s/podcasts";
    private static final String RSS_FETCH_FEED_URL_SUFFIX = "/fetchfeed";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Config config;

    @RequestMapping(value = "/users/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> fetchPodcasts(@PathVariable("name") String name) {
        ResponseEntity<List<Podcast>> exchange = fetchPodcastsForUser(name);
        List<Podcast> podcasts = exchange.getBody();

        return ResponseEntity.ok(podcasts.stream()
                .map(this::fetchPodcastLinks)
                .map(HttpEntity::getBody)
                .flatMap(Collection::stream)
                .collect(toList()));
    }

    @RequestMapping(value = "/users/{name}/podcast", method = RequestMethod.POST)
    public ResponseEntity<Void> addPodcast(@PathVariable("name") String name, @RequestBody Podcast podcast){
        restTemplate.exchange(String.format(config.getUserServiceUrl().concat(USER_PODCASTS_URL_SUFFIX), name), HttpMethod.POST,
                new HttpEntity<Object>(podcast), Void.class);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<String>> fetchPodcastLinks(Podcast podcast) {
        return restTemplate.exchange(config.getRssServiceUrl().concat(RSS_FETCH_FEED_URL_SUFFIX),
                HttpMethod.POST, new HttpEntity<>(podcast), new ParameterizedTypeReference<List<String>>() {
                });
    }

    private ResponseEntity<List<Podcast>> fetchPodcastsForUser(String name) {
        return restTemplate.exchange(String.format(config.getUserServiceUrl().concat(USER_PODCASTS_URL_SUFFIX), name),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Podcast>>() {
                });
    }

}
