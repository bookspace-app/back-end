package com.bookspace.bookspace.user;

import java.time.LocalDate;
import java.util.List;

import com.bookspace.bookspace.enums.Rank;

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
                LocalDate.now(),
                Rank.QUEEN,
                LocalDate.now()
            );
            repository.saveAll(List.of(demo));

        };

    }
}