package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;
import com.example.bookspace.services.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<TagOutput> getAllTags(){
        return tagService.getTags();
    }

    @GetMapping(path = "{idTag}")   
	public TagOutput getTagById(@PathVariable("idTag") Long idTag) {
        return tagService.getTag(idTag);
    }

    @PostMapping
    public TagOutput postTag(@RequestBody TagInput tagDetails) {
        return tagService.postTag(tagDetails);
    }

    @PutMapping(path = "{IdTag}") 
    public void updateTag(@PathVariable("IdTag") Long IdTag,
                                      @RequestParam(required = false) User author,
                                      @RequestParam(required = false) List<Publication> taggedPublications,
                                      @RequestParam(required = false) List<User> favTags){
        tagService.updateTag(IdTag,author,taggedPublications,favTags);
    }

    @DeleteMapping(path = "{idTag}")
    public void deleteTag(@PathVariable("idTag") Long idTag){
        tagService.deleteTag(idTag);
    }
}
