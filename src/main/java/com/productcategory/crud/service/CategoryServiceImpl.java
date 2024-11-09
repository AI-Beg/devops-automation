package com.productcategory.crud.service;

import org.springframework.stereotype.Service;

import com.productcategory.crud.entity.Category;
import com.productcategory.crud.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryRepo categoryRepo;

	CategoryServiceImpl(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

}
