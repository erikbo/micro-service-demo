package com.ef;

import com.ef.persistence.Podcast;
import com.ef.persistence.User;
import com.ef.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by eboh on 18/01/17.
 */
@RestController
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Config config;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = GET)
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @RequestMapping(value = "/users/{name}/podcasts", method = GET)
    public ResponseEntity<List<Podcast>> getPodcasts(@PathVariable ("name") String name){
        User user;
        try{
            user = userService.findByName(name);
        } catch (NoSuchElementException exception){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getPodcasts());
    }

    @RequestMapping(value = "/users/{name}/podcasts", method = POST)
    public ResponseEntity<Void> addPodcast(@PathVariable ("name") String name, @RequestBody Podcast podcast){
        final User user = userService.findByName(name);

        final List<Podcast> podcasts = user.getPodcasts();
        podcasts.add(podcast);

        userService.save(user);

        return ResponseEntity.ok().build();
    }

}
