package com.productcategory.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productcategory.crud.custommapper.DTOMapper;
import com.productcategory.crud.dto.ProductDTO;
import com.productcategory.crud.entity.Product;
import com.productcategory.crud.exception.ProductNotFoundException;
import com.productcategory.crud.repo.CategoryRepo;
import com.productcategory.crud.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepo productRepo;
	private CategoryRepo categoryRepo;
	private DTOMapper dtoMapper;

	public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, DTOMapper dtoMapper) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.dtoMapper = dtoMapper;
	}

	@Override
	public ProductDTO getProductById(int productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId, "Product not found!"));
		return DTOMapper.toProductDTO(product);

	}

	@Override
	public List<ProductDTO> findProductByCategoryId(int categoryId, Pageable pageable) {
		List<Product> productList = productRepo.findProductByCategoryId(categoryId, pageable);
//		List<Product> productList = productRepo.findProductByCateId(categoryId, pageable);
//		List<Product> productList2 = productRepo.findByCategoryId(categoryId);
		return productList.stream().map(DTOMapper::toProductDTO).collect(Collectors.toList());

	}

	@Override
	public List<ProductDTO> findProductByCategoryIdPage(int categoryId, Pageable pageable) {
		Page<Product> productList = productRepo.findByCategoryId(categoryId, pageable);
		return productList.getContent().stream().map(DTOMapper::toProductDTO).collect(Collectors.toList());

	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<Product> productList = productRepo.findAll();
		return productList.stream().map(DTOMapper::toProductDTO).collect(Collectors.toList());
	}

	@Override
	public Product addProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO updateProduct(int productId, ProductDTO productDTO) {

		Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId,
				"Product could not update as it is not avialble in database!"));

		// Update fields
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(
				categoryRepo.findById(productDTO.getCategoryId()).orElseThrow(() -> new ProductNotFoundException()));

		productRepo.save(product);

		return productRepo.findById(productId).map(existingProduct -> {
			// Use DTOMapper to update fields in existingProduct from productDTO
			dtoMapper.updateProductFromDTO(productDTO, existingProduct);
			return productRepo.save(existingProduct);
		}).map(DTOMapper::toProductDTO).orElseThrow(() -> new ProductNotFoundException(productId, "Product not found"));

//		return DTOMapper.toProductDTO(product);

//		return productRepo.findById(productId).map(p -> {
//			p.setName(productDTO.getName());
//			p.setPrice(productDTO.getPrice());
//			p.setCategory(DTOMapper.toCategory(productDTO.getCategory()));
//			return productRepo.save(p);
//		}).map(DTOMapper::toProductDTO).orElseThrow(() -> new ProductNotFoundException(productId, "Product not found"));

//		return productRepo.findById(productId).map(DTOMapper::toProductDTO)
//				.orElseThrow(() -> new ProductNotFoundException(productId,
//						"Product could not update as it is not avialble in database!"));

	}

	@Override
	public Product deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
