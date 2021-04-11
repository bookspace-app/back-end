package com.example.bookspace.PublicationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.services.PublicationService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Publication;
import com.example.bookspace.repositories.UserRepository;
import com.example.bookspace.repositories.PublicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PublicationServiceTest {

    PublicationService publicationService;

    @BeforeEach
    void setup() {
        PublicationRepository publicationRepository = Mockito.mock(PublicationRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        List<Publication> publications = new ArrayList<>();

        User u1 = new User(new UserInput("email1", "name1", "username1", "password1"));
        Publication p1 = new Publication(new PublicationInput("title1", "content1", 1L, "category1"), u1);

		User u2 = new User(new UserInput("email2", "name2", "username2", "password2"));
        Publication p2 = new Publication(new PublicationInput("title2", "content2", 2L, "category2"), u2);

        publications.add(p1);
        publications.add(p2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<Publication> op1 = Optional.of(p1);

        when(publicationRepository.findAll()).thenReturn(publications);
        when(publicationRepository.getOne(1L)).thenReturn(p1);
        when(userRepository.getOne(1L)).thenReturn(u1);
        when(userRepository.findById(1L)).thenReturn(ou1);
        when(publicationRepository.findById(1L)).thenReturn(op1);
        when(publicationRepository.existsById(1L)).thenReturn(true);

        this.publicationService = new PublicationService(publicationRepository, userRepository);
    }

    @Test
    void testgetPublications() {

        List<PublicationOutput> result = new ArrayList<>();
		result = publicationService.getPublications();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getTitle())
                        .isEqualTo("title1");
        assertThat(result.get(1).getTitle())
                        .isEqualTo("title2");
    }

    @Test
    void testgetPublication() {

        PublicationOutput result;
		result = publicationService.getPublication(1L);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title1");
    }

    @Test
    void testpostPublication() {

        PublicationInput pi1 = new PublicationInput("titleNew", "contentNew", 1L, "categoryNew");

        PublicationOutput result;
		result = publicationService.postPublication(pi1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("titleNew");
    }

    @Test
    void testupdatePublication() {

        PublicationInput pi1 = new PublicationInput("titleNew", "contentNew", 1L, "categoryNew");

        PublicationOutput result;
		result = publicationService.updatePublication(1L, pi1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("titleNew");
    }

    @Test
    void testdeletePublication() {

        publicationService.deletePublication(1L);
    }

    @Test
    void testgetVotedByUsers() {

        List<UserOutput> result = new ArrayList<>();
		result = publicationService.getVotedByUsers(1L);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void testgetFavUsers() {

        List<UserOutput> result = new ArrayList<>();
		result = publicationService.getFavUsers(1L);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void testpostFavUser() {

        UserOutput result = new UserOutput();
		result = publicationService.postFavUser(1L, 1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testpostLike() {

        PublicationOutput result;
		result = publicationService.postLike(1L);

        assertThat(result.getTitle()).isEqualTo("title1");
    }

    @Test
    void testpostDislike() {

        PublicationOutput result;
		result = publicationService.postLike(1L);

        assertThat(result.getTitle()).isEqualTo("title1");
    }
}