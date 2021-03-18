package com.bookspace.bookspace.theme;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class CategoryConfig {

    @Bean
    CommandLineRunner commandLineRunnerConfig(CategoryRepository repository) {
        return args ->  {
            Category demo = new Category(
            );
            repository.saveAll(List.of(demo));
        };
    }
}