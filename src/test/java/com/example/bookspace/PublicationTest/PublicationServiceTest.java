package com.example.bookspace.PublicationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.services.PublicationService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Publication;
import com.example.bookspace.repositories.UserRepository;
import com.example.bookspace.repositories.PublicationRepository;

import java.time.LocalDate;
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

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("ACTION");
        List<Category> cateogories = Category.getCategories(fav_cat);
		u1.setFavCategories(cateogories);

        List<Publication> pub = new ArrayList<>();
        Publication p1 = new Publication("title1", "content1", u1, Category.ACTION);
        pub.add(p1);
        u1.setPublications(pub);

        List<User> fav_by = new ArrayList<>();

		User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        List<String> fav_cat2 = new ArrayList<>();
        fav_cat2.add("LOVE");
        List<Category> cateogories2 = Category.getCategories(fav_cat2);
		u2.setFavCategories(cateogories2);

        List<Publication> pub2 = new ArrayList<>();
        Publication p2 = new Publication("title2", "content2", u2, Category.LOVE);
        pub2.add(p2);
        u2.setPublications(pub2);

        fav_by.add(u2);
        p1.setFavouriteBy(fav_by);

        User u3 = new User("email3", "name3", "username3", "password3", LocalDate.now());

        publications.add(p1);
        publications.add(p2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<User> ou3 = Optional.of(u3);
        Optional<Publication> op1 = Optional.of(p1);

        when(publicationRepository.findAll()).thenReturn(publications);
        when(userRepository.existsById(3L)).thenReturn(true);
        when(userRepository.findById(3L)).thenReturn(ou3);
        when(publicationRepository.getOne(1L)).thenReturn(p1);
        when(publicationRepository.findById(1L)).thenReturn(op1);
        when(publicationRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(3L)).thenReturn(u3);


        when(userRepository.findById(1L)).thenReturn(ou1);

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
    void testpostPublication() throws Exception {

        List<Long> nl = new ArrayList<>();
        PublicationInput pi1 = new PublicationInput("title3", "content3", 3L, "POTENTIAL", nl, nl);

        PublicationOutput result;
		result = publicationService.postPublication(pi1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title3");
    }

    @Test
    void testgetPublication() {

        PublicationOutput result;
		result = publicationService.getPublication(1L);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title1");
    }

    @Test
    void testputPublication() {

        List<Long> nl = new ArrayList<>();
        PublicationInput pi1 = new PublicationInput("titleNEW", "content3", 3L, "POTENTIAL", nl, nl);

        PublicationOutput result;
		result = publicationService.putPublication(1L, pi1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("titleNEW");
    }

    @Test
    void testdeletePublication() {

        publicationService.deletePublication(1L);
    }

    @Test
    void testgetLikedUsers() {
        //Not implemented yet
    }

    @Test
    void testpostLike() {
        //Not implemented yet
    }

    @Test
    void testdeleteLike() {
        //Not implemented yet
    }

    @Test
    void testgetDislikedUsers() {
        //Not implemented yet
    }

    @Test
    void testpostDislike() {
        //Not implemented yet
    }

    @Test
    void testdeleteDislike() {
        //Not implemented yet
    }

    @Test
    void testgetFavUsers() {

        List<UserOutput> result = new ArrayList<>();
		result = publicationService.getFavUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("username2");
    }

    @Test
    void testpostFavUsers() {

        UserOutput result = publicationService.postFavUser(1L, 3L);

        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    void testdeleteFavUser() {
        //Not implemented yet
    }

    @Test
    void testgetComments() {
        //Not implemented yet
    }

    @Test
    void testgetMentions() {
        //Not implemented yet
    }

    @Test
    void testgetTags() {
        //Not implemented yet
    }
}