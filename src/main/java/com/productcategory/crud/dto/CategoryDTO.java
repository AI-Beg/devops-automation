package com.productcategory.crud.dto;

import java.util.List;

public class CategoryDTO {
	private int id;
	private String name;
	private List<Integer> productIds; // Only IDs to avoid recursion

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

	// Getters and Setters

}