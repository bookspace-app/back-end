package com.example.bookspace.beans;

import java.util.List;

import com.example.bookspace.models.Tag;
import com.example.bookspace.repositories.TagRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TagConfig {

    @Bean
    CommandLineRunner commandLineRunnerTag(TagRepository repository) {
        return args ->  {
            Tag demo = new Tag(
                "tag1"
            );
            repository.saveAll(List.of(demo));
        };
    }
}