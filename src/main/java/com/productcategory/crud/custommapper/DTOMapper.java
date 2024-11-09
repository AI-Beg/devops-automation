package com.productcategory.crud.custommapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.productcategory.crud.dto.CategoryDTO;
import com.productcategory.crud.dto.ProductDTO;
import com.productcategory.crud.entity.Category;
import com.productcategory.crud.entity.Product;
import com.productcategory.crud.exception.ProductNotFoundException;
import com.productcategory.crud.repo.CategoryRepo;

@Component
public class DTOMapper {

	private CategoryRepo categoryRepo;

	DTOMapper(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	public static ProductDTO toProductDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		if (product.getCategory() != null) {
			dto.setCategoryId(product.getCategory().getId());
		}
		return dto;
	}

	public Product toProduct(ProductDTO productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		if (productDto.getCategoryId() != null) {
			product.setCategory(categoryRepo.findById(productDto.getCategoryId())
					.orElseThrow(() -> new ProductNotFoundException()));

		}
		return product;
	}

	public static CategoryDTO toCategoryDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setProductIds(category.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
		return dto;
	}

	public static Category toCategory(CategoryDTO categoryDto) {
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setProducts(null);
		return category;
	}

	public void updateProductFromDTO(ProductDTO productDTO, Product product) {
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(
				categoryRepo.findById(productDTO.getCategoryId()).orElseThrow(() -> new ProductNotFoundException()));
	}
}