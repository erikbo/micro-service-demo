package com.ef.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by eboh on 23/01/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

    List<User> findAll();

}