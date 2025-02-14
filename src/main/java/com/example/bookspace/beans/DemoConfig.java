package com.example.bookspace.beans;

import java.time.LocalDate;
import java.util.List;

import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.enums.Rank;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.CommentRepository;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    User user, user2, user3;
    Publication publication;
    Tag tag;
    Category category;
    Comment comment;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PublicationRepository publicationRepository, TagRepository tagRepository, CommentRepository commentRepository) {
        return args ->  {
            UserInput userDetails = new UserInput("demoEmail", "demoName", "demoUsername", "demoPassword", LocalDate.of(1998, 12, 2), "demoDescriprion", List.of("action"));
            user = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
            user.setToken("AUTH");
            userDetails = new UserInput("demoEmail2", "demoName2", "demoUsername2", "demoPassword2", LocalDate.of(2000, 6, 1), "demoDescriprion2", List.of("romantic"));
            user2 = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
            user2.setToken("AUTH");
            userDetails = new UserInput("Queen@gmail.com", "Queen", "Queen", "Queen", LocalDate.of(1915, 4, 3), "I am the most powerful figure (not only in chess)", List.of("terror"));
            user3 = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
            user3.setToken("AUTH");
            user3.setRank(Rank.QUEEN);
    
            category = Category.action;
            user.addFavCategory(Category.action);
            user2.addFavCategory(Category.SciFi);
            user3.addFavCategory(Category.terror);
            user = userRepository.save(user);
            user2 = userRepository.save(user2);
            user3 = userRepository.save(user3);
            PublicationInput publicationDetails = new PublicationInput("demoTitle", "demoContent", user.getId(), category.name(), null, null);
            publication = new Publication(publicationDetails.getTitle(), publicationDetails.getContent(), user, category);
            publication = publicationRepository.save(publication);
            TagInput tagDetails = new TagInput("demoTag", user.getId(), publication.getId());
            tag = new Tag(tagDetails.getName(), user);
            publication.addTag(tag);
            tag.getPublications().add(publication);
            tagRepository.save(tag); 
            
            CommentInput commentDetails = new CommentInput("contentDemo", 1L, 1L, null, null);
            Comment comment = new Comment(commentDetails.getContent(), user, publication);
            comment = commentRepository.save(comment);

            publication.addDirectComment();
            publication = publicationRepository.save(publication);

            
        };
    }   
}
