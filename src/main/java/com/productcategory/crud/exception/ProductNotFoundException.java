package com.productcategory.crud.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productId;
	private String errorMessage;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int errorCode) {
		this.productId = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ProductNotFoundException(int errorCode, String errorMessage) {
		super();
		this.productId = errorCode;
		this.errorMessage = errorMessage;
	}

	public ProductNotFoundException() {
		super();
	}

}
