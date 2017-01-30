package com.ef.service;

import com.ef.persistence.Podcast;
import com.ef.persistence.User;
import com.ef.persistence.UserRepository;
import com.google.common.base.Throwables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by eboh on 30/01/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void save_proxiesSaveCallToRepository(){
        User user = new User("test", singletonList(new Podcast("test", "http://test-url")));

        userService.save(user);

        verify(userRepository).save(user);
    }

    @Test
    public void findall_proxiestRequestToRepository(){
        userService.findAll();

        verify(userRepository).findAll();
    }

    @Test
    public void findByName_returnsExpectedUser(){
        User user = new User("test", singletonList(new Podcast("test", "http://test-url")));

        given(userRepository.findByName("test")).willReturn(Optional.of(user));

        User actualUser = userService.findByName("test");

        assertThat(actualUser).isEqualTo(user);
    }

    @Test(expected = NoSuchElementException.class)
    public void findByName_returnsNoSuchElementException_givenNoSuchUser(){
        given(userRepository.findByName("test")).willReturn(Optional.empty());

        userService.findByName("test");
    }
}