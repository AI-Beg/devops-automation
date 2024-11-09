package com.productcategory.crud.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.productcategory.crud.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	/** All 3 working same */
	List<Product> findProductByCategoryId(int categoryId, Pageable pageable);

	List<Product> findByCategoryId(int categoryId);

	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
	List<Product> findProductByCateId(@Param("categoryId") int categoryId, Pageable pageable);

	/****/
	Page<Product> findByCategoryId(int categoryId, Pageable pageable);
}
