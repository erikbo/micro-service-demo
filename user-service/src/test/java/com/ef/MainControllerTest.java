package com.ef;

import com.ef.persistence.Podcast;
import com.ef.persistence.User;
import com.ef.service.UserService;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;

/**
 * Created by eboh on 30/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private MainController mainController;

    private User user;

    @Before
    public void setUp(){
     user = new User("test-user", singletonList(new Podcast("test-podcast", "http://some-url")));
    }

    @Test
    public void getAllUsers_shouldReturnAllUsers(){
        List<User> expectedUsers = singletonList(user);
        ResponseEntity<List<User>> exptectedResponse = ResponseEntity.ok(expectedUsers);

        given(userService.findAll()).willReturn(expectedUsers);

        ResponseEntity<List<User>> actualResponse = mainController.getAllUsers();

        assertThat(actualResponse).isEqualTo(exptectedResponse);
    }

    @Test
    public void getPodcasts_shouldReturnUsersPodcasts(){
        given(userService.findByName(eq("test"))).willReturn(user);

        ResponseEntity<List<Podcast>> expectedResponse = ResponseEntity.ok(user.getPodcasts());

        ResponseEntity<List<Podcast>> actualResponse = mainController.getPodcasts("test");

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void getPodcasts_shouldReturnNotFound_givenUserDoesntExist(){
        doThrow(new NoSuchElementException("not found")).when(userService).findByName(eq("no-user"));

        ResponseEntity<List<Podcast>> expectedResponseEntity = ResponseEntity.notFound().build();

        ResponseEntity<List<Podcast>> actualResponse = mainController.getPodcasts("no-user");

        assertThat(actualResponse).isEqualTo(expectedResponseEntity);
    }

}