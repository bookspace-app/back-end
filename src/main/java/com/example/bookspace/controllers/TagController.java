package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.models.Publication;
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
//Controller class for Tag
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    //An endpoint that returns all created Tags in DB
    public List<TagOutput> getAllTags(){
        return tagService.getTags();
    }

<<<<<<< HEAD
    @GetMapping(path = "{IdTag}")   
	public Optional<Tag> getTagById(@PathVariable("IdTag") String IdTag) {
        return tagService.getTag(IdTag);
    }

    @PutMapping(path = "{IdTag}") 
    public void updateTag(@PathVariable("IdTag") String IdTag,
=======
    @GetMapping(path = "{idTag}")  
    //An endpoint that when is given an ID returns the Tag associated with it in the DB 
	public TagOutput getTagById(@PathVariable("idTag") Long idTag) {
        return tagService.getTag(idTag);
    }

    @PostMapping
    //An endpoint that when is given a Tag details it posts the Tag and returns it
    public TagOutput postTag(@RequestBody TagInput tagDetails) throws Exception {
        return tagService.postTag(tagDetails);
    }

    @PutMapping(path = "{IdTag}") 
    //An endpoint that when is given an ID and some Tag details it updates the Tag associated with it in the DB and returns it
    public void updateTag(@PathVariable("IdTag") Long IdTag,
>>>>>>> development
                                      @RequestParam(required = false) User author,
                                      @RequestParam(required = false) List<Publication> taggedPublications,
                                      @RequestParam(required = false) List<User> favTags){
        tagService.updateTag(IdTag,author,taggedPublications,favTags);
    }

<<<<<<< HEAD
    @DeleteMapping(path = "{IdTag}")
    public void deleteTag(@PathVariable("IdTag") String IdTag){
        tagService.deleteTag(IdTag);
=======
    @DeleteMapping(path = "{idTag}")
    //An endpoint that when is given an ID it deletes the Tag associated with it in the DB
    public void deleteTag(@PathVariable("idTag") Long idTag){
        tagService.deleteTag(idTag);
>>>>>>> development
    }
}
