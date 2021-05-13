package com.example.bookspace.repositories;

import java.util.Optional;

import com.example.bookspace.models.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Repository class for Tag
public interface TagRepository extends JpaRepository<Tag, Long> {

    //It finds the Tag in the DB associated with the given attribute {name}
    Optional<Tag> findTagByName(String name);

    Tag getTagByName(String name);
}