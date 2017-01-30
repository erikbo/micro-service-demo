package com.ef.service;

import com.ef.persistence.User;
import com.ef.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by eboh on 23/01/17.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByName(String name){
        return userRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("No user with that name was found"));
    }

}
