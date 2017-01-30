package com.ef;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by eboh on 30/01/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, Config.class})
public class UserControllerTest {

    private static final String USER_PODCASTS_URL_SUFFIX = "/users/test/podcasts";

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private Config config;

    @Autowired
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void fetchPodcasts_getsTheUsersPodcasts() throws Exception{
        String userServicePodcastUrl = config.getUserServiceUrl().concat(USER_PODCASTS_URL_SUFFIX);

        List<Podcast> expectedPodcasts = singletonList(new Podcast("test-podcast", "http://some-url"));

        mockRestServiceServer.expect(requestTo(userServicePodcastUrl)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(objectMapper.writeValueAsString(expectedPodcasts), MediaType.APPLICATION_JSON));

        mockRestServiceServer.expect(requestTo(config.getRssServiceUrl().concat("/fetchfeed"))).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(objectMapper.writeValueAsString(singletonList("test-podcast-episode")), MediaType.APPLICATION_JSON));

        List<String> actualPodcastEpisodes = userController.fetchPodcasts("test").getBody();

        assertThat(actualPodcastEpisodes).isEqualTo(singletonList("test-podcast-episode"));
    }

    @Test
    public void addPodcast_proxiesRequestToUserService() throws Exception{
        String userServicePodcastUrl = config.getUserServiceUrl().concat(USER_PODCASTS_URL_SUFFIX);
        Podcast expectedPodcast = new Podcast("test", "http://some-test-url");

        mockRestServiceServer.expect(requestTo(userServicePodcastUrl)).andExpect(method(HttpMethod.POST))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedPodcast)))
                .andRespond(withSuccess());

        userController.addPodcast("test", expectedPodcast);

        mockRestServiceServer.verify();

    }

}