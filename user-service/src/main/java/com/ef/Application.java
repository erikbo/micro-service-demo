package com.ef;

import com.ef.persistence.Podcast;
import com.ef.persistence.User;
import com.ef.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Created by eboh on 18/01/17.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService){
        return (args) -> {
            final List<Podcast> podcasts = Collections.singletonList(new Podcast("Lore", "http://lorepodcast.libsyn.com/rss"));

            final User user = new User("test", podcasts);
            userService.save(user);
        };
    }

}
