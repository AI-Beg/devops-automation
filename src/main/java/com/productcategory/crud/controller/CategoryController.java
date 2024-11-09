package com.productcategory.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcategory.crud.entity.Category;
import com.productcategory.crud.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	CategoryService categoryservice;

	CategoryController(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}

	@PostMapping("/save")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		return new ResponseEntity<>(categoryservice.saveCategory(category), HttpStatus.CREATED);
	}

}
