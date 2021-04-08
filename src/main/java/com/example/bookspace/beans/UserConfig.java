package com.example.bookspace.beans;

import java.util.List;

import com.example.bookspace.Inputs.UserInput;
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
            UserInput userDetails = new UserInput("demoEmail", "demoName", "demoUsername", "demoPassword");
            User demo = new User(userDetails);
            repository.saveAll(List.of(demo));
        };

    }
}