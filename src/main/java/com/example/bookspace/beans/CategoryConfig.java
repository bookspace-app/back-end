package com.example.bookspace.beans;

import java.util.List;

import com.example.bookspace.models.Category;
import com.example.bookspace.repositories.CategoryRepository;

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