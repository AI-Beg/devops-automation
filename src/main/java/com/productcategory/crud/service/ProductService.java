package com.productcategory.crud.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.productcategory.crud.dto.ProductDTO;
import com.productcategory.crud.entity.Product;

public interface ProductService {

	ProductDTO getProductById(int productId);

	List<ProductDTO> getAllProduct();

	Product addProduct();

	ProductDTO updateProduct(int productId, ProductDTO productDTO);

	Product deleteProduct(int productId);

	List<ProductDTO> findProductByCategoryId(int categoryId, Pageable pageable);

	List<ProductDTO> findProductByCategoryIdPage(int categoryId, Pageable pageable);

}
