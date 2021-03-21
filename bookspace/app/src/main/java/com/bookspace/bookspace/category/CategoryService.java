package com.bookspace.bookspace.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}

	public Optional<Category> getCategory(Long Id) {
		boolean exists = categoryRepository.existsById(Id);
		if(!exists) throw new IllegalStateException("The Category with Id " + Id + " does not exist");
        return categoryRepository.findById(Id);
    }

	public void deleteCategory(Long categoryId){
		boolean b = categoryRepository.existsById(categoryId);
		if(!b) {
			throw new IllegalStateException("Category with id " + categoryId + " does not exists");
		}
		categoryRepository.deleteById(categoryId);
	}
}
