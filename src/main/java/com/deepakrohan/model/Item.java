package com.deepakrohan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
	"tcin",
	"product_description"
})
public class Item {

	@JsonProperty("tcin")
	private String tcin;
	@JsonProperty("product_description")
	private ProductDescription productDescription;

	@JsonProperty("tcin")
	public String getTcin() {
		return tcin;
	}

	@JsonProperty("tcin")
	public void setTcin(String tcin) {
		this.tcin = tcin;
	}

	@JsonProperty("product_description")
	public ProductDescription getProductDescription() {
		return productDescription;
	}

	@JsonProperty("product_description")
	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}
	
	@Override
	public String toString() {
		return "Item [tcin=" + tcin + ", productDescription=" + productDescription + "]";
	}



}
