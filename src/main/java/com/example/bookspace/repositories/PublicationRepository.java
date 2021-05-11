package com.example.bookspace.repositories;
import java.util.List;
import java.util.Optional;

import com.example.bookspace.enums.Category;
import com.example.bookspace.models.Publication;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Optional<Publication> findPublicationByContent(String content);

    List<Publication> findByCategory(Category category);



}