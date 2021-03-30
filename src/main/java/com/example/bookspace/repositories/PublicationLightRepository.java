package com.example.bookspace.repositories;

import com.example.bookspace.models.Publication;

import org.springframework.data.repository.CrudRepository;

public interface PublicationLightRepository  extends CrudRepository<Publication, Long>{
    
}
