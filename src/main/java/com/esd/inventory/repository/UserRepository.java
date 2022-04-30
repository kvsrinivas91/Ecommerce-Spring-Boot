package com.esd.inventory.repository;

import com.esd.inventory.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer > {

    Optional<User> findUserByEmail(String email);

}
