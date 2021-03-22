package com.example.bookspace.beans;

import java.time.LocalDate;
import java.util.List;

import com.example.bookspace.models.User;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args ->  {
            User demo = new User(
                "email",
                "demo",
                "username",
                LocalDate.now(),
                "description"
            );
            repository.saveAll(List.of(demo));

        };

    }
}