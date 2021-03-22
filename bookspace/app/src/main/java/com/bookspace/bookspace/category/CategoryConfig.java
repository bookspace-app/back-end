package com.bookspace.bookspace.category;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CategoryConfig {

    @Bean
    CommandLineRunner commandLineRunnerCategory(CategoryRepository repository) {
        return args ->  {
            Category demo = new Category(
            );
            repository.saveAll(List.of(demo));

        };
    }
}