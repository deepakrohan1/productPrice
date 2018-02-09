package com.deepakrohan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"product"
})
public class ProductDataBO {

	@JsonProperty("product")
	private Product product;

	@JsonProperty("product")
	public Product getProduct() {
		return product;
	}

	@JsonProperty("product")
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CloudantData [product=" + product + "]";
	}



}


