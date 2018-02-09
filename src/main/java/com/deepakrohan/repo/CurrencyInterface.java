package com.deepakrohan.repo;

import com.deepakrohan.model.ProductDataBO;
import com.deepakrohan.model.ProductDetails;

public interface CurrencyInterface{
	
	ProductDetails findOne(ProductDataBO productDetail);
	
	boolean updateOne(String productId, Double productPrice);


}