package com.example.bookspace.repositories;

import java.util.Optional;

import com.example.bookspace.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

    User getUserByUsername(String username);
}
