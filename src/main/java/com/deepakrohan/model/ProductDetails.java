package com.deepakrohan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "currencyDetails")
public class ProductDetails {
	
	@Id
	String prodId;
	Double prodPrice;
	String prodCurrency, prodName;
	
	public ProductDetails() {};
	


	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdCurrency() {
		return prodCurrency;
	}

	public void setProdCurrency(String prodCurrency) {
		this.prodCurrency = prodCurrency;
	}

	public Double getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Double prodPrice) {
		this.prodPrice = prodPrice;
	}



	public String getProdName() {
		return prodName;
	}



	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Override
	public String toString() {
		return "CurrencyDetails [prodId=" + prodId + ", prodPrice=" + prodPrice + ", prodCurrency=" + prodCurrency
				+ ", prodName=" + prodName + "]";
	}



	public ProductDetails(String prodId, Double prodPrice, String prodCurrency, String prodName) {
		super();
		this.prodId = prodId;
		this.prodPrice = prodPrice;
		this.prodCurrency = prodCurrency;
		this.prodName = prodName;
	}


	

}
