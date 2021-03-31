package com.example.bookspace.controllers;

import java.util.List;
import java.util.Optional;

import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.services.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/tags")

public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.getTags();
    }

    @GetMapping(path = "{IdTag}")   
	public Optional<Tag> getTagById(@PathVariable("IdTag") String IdTag) {
        return tagService.getTag(IdTag);
    }

    @PutMapping(path = "{IdTag}") 
    public void updateTag(@PathVariable("IdTag") String IdTag,
                                      @RequestParam(required = false) User author,
                                      @RequestParam(required = false) List<Publication> tagged_publications,
                                      @RequestParam(required = false) List<User> preferedTags){
        tagService.updateTag(IdTag,author,tagged_publications,preferedTags);
    }

    @DeleteMapping(path = "{IdTag}")
    public void deleteTag(@PathVariable("IdTag") String IdTag){
        tagService.deleteTag(IdTag);
    }
}
