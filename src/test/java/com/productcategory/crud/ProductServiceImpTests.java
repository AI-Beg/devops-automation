package com.productcategory.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.productcategory.crud.dto.ProductDTO;
import com.productcategory.crud.entity.Product;
import com.productcategory.crud.exception.ProductNotFoundException;
import com.productcategory.crud.repo.ProductRepo;
import com.productcategory.crud.service.ProductServiceImpl;

@SpringBootTest
class ProductServiceImpTests {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepo productRepo;

	@Mock
	private Product product;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		product = new Product(); // Create a sample product
		product.setId(1);
		product.setName("Test Product");
		product.setPrice(300);
		// Set other properties if necessary
	}

	@Test
	void testGetProductById_Success() {
		// Arrange
		int productId = 1;
		when(productRepo.findById(productId)).thenReturn(Optional.of(product));

		// Act
		ProductDTO retrievedProduct = productServiceImpl.getProductById(productId);

		// Assert
		assertNotNull(retrievedProduct);
		assertEquals(productId, retrievedProduct.getId());
		assertEquals("Test Product", retrievedProduct.getName());
		verify(productRepo, times(1)).findById(productId);
	}

	@Test
	void testGetProductById_NotFound() {
		// Arrange
		int productId = 2;
		when(productRepo.findById(productId)).thenReturn(Optional.empty());

		// Act & Assert
		ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
			productServiceImpl.getProductById(productId);
		});

		assertEquals("Product not found!", exception.getErrorMessage());
		assertEquals(productId, exception.getProductId()); // Assuming you have a method to get the productId
		verify(productRepo, times(1)).findById(productId);
	}

}
