package com.productcategory.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcategory.crud.dto.ProductDTO;
import com.productcategory.crud.entity.Product;
import com.productcategory.crud.repo.ProductRepo;
import com.productcategory.crud.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	ProductRepo productRepo;
	private ProductService productService;

	ProductController(ProductService productService) {
		this.productService = productService;
	}

	/** Retrieve by ID ***/
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {

		System.out.println("Getting product by ID...." + id);
		ProductDTO product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	/** Retrieve by ID ***/
	@GetMapping("category/{cid}")
	public ResponseEntity<List<ProductDTO>> getProductByCategoryId(@PathVariable("cid") Integer id,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		System.out.println("Getting product by Category ID with pagination...." + id);
		List<ProductDTO> productList = productService.findProductByCategoryIdPage(id, pageable);
		return new ResponseEntity<>(productList, HttpStatus.OK);

	}

	/** Retrieve List ***/
	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> getAllProduct() {
		System.out.println("Getting all product...");
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}

	/** Create ***/
	@PostMapping("/addresponse")
	public ResponseEntity<Product> addResponseProduct(@RequestBody Product product) {
		System.out.println("Adding/Creating product...");
		productRepo.save(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);

	}

	/** Create ***/
	@PostMapping("/add")
	public void addProduct(@RequestBody Product product) {
		System.out.println("Adding/Creating product....");
		productRepo.save(product);

	}

	/** update ***/
	@PutMapping("/update/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
		productService.updateProduct(id, productDTO);
//		return new ResponseEntity<>(productDTO, HttpStatus.OK);
		return ResponseEntity.ok(productService.updateProduct(id, productDTO));

	}

	/** Delete ***/
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
		System.out.println("Deleting product....");
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {
			Product product = optional.get();
			productRepo.delete(product);
			return new ResponseEntity<Product>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}

	}
}
