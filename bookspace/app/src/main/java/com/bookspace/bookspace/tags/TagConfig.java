package com.bookspace.bookspace.tags;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TagConfig {

    @Bean
    CommandLineRunner commandLineRunnerTag(TagRepository repository) {
        return args ->  {
            Tag demo = new Tag(
            );
            repository.saveAll(List.of(demo));
        };
    }
}