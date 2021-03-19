package com.bookspace.bookspace.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getCategory(){
		return categoryRepository.findAll();
	}

	public void deleteCategory(Long categoryId){
		boolean b = categoryRepository.existsById(categoryId);
		if(!b) {
			throw new IllegalStateException("Category with id " + categoryId + " does not exists");
		}
		categoryRepository.deleteById(categoryId);
	}
}
