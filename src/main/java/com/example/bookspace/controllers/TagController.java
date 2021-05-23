package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.services.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(path = "{tagName}")  
    //An endpoint that when is given an ID returns the Tag associated with it in the DB 
	public TagOutput getTagById(@PathVariable("tagName") String tagName) {
        return tagService.getTag(tagName);
    }

    @GetMapping(path = "/tagname/{tagName}")  
    //An endpoint that when is given an tagName returns the Tag associated with it in the DB 
	public TagOutput getTagByName(@PathVariable("tagName") String name) throws Exception {
        return tagService.getTagByTagName(name);
    }

    @PostMapping
    //An endpoint that when is given a Tag details it posts the Tag and returns it
    public TagOutput postTag(@RequestBody TagInput tagDetails) throws Exception {
        return tagService.postTag(tagDetails);
    }

    @PutMapping(path = "{tagName}") 
    //An endpoint that when is given an ID and some Tag details it updates the Tag associated with it in the DB and returns it
    public void updateTag(@RequestBody TagInput tagDetails){
        tagService.updateTag(tagDetails);
    }

    @DeleteMapping(path = "{tagName}")
    //An endpoint that when is given an ID it deletes the Tag associated with it in the DB
    public void deleteTag(@PathVariable("tagName") String tagName) throws Exception{
        tagService.deleteTag(tagName);
    }
}
