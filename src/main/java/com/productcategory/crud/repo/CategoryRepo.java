package com.productcategory.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcategory.crud.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
