package com.example.bookspace.beans;

import java.time.LocalDate;
import java.util.List;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    User user;
    Publication publication;
    Tag tag;
    Category category;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PublicationRepository publicationRepository, TagRepository tagRepository) {
        return args ->  {
            UserInput userDetails = new UserInput("demoEmail", "demoName", "demoUsername", "demoPassword", LocalDate.of(1998, 12, 2), "demoDescriprion", List.of("ACTION"));
            user = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
            user = userRepository.save(user);
            category = Category.Accion;
            PublicationInput publicationDetails = new PublicationInput("demoTitle", "demoContent", user.getId(), category.name(), null, null);
            publication = new Publication(publicationDetails.getTitle(), publicationDetails.getContent(), user, category);
            publication = publicationRepository.save(publication);
            TagInput tagDetails = new TagInput("demoTag", user.getId(), publication.getId());
            tag = new Tag(tagDetails.getName(), user, publication);
            tagRepository.save(tag);            
        };

    }

    
}
