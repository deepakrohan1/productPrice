package com.deepakrohan.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.deepakrohan.model.ProductDataBO;
import com.deepakrohan.model.ProductDetails;
import com.deepakrohan.repo.CurrencyInterface;

@RestController
public class ProductController {
	@Value("${redsky.url}")
	private String redskyUrl;

	@Autowired
	RestTemplate restTemplate;


	public static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProductController.class);

	@Autowired
	CurrencyInterface currencyInterface;


	@RequestMapping("/product/{productId}")
	public ResponseEntity<ProductDetails> getProduct(@PathVariable String productId) {
		ProductDetails productDetails = null;
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		try {
			ProductDataBO productData = restTemplate.getForObject(redskyUrl, ProductDataBO.class, productId);
			productDetails = currencyInterface.findOne(productData);
		}catch(NullPointerException ex) {
			logger.error("No such object found");
		}catch(HttpClientErrorException ex) {
			logger.error("failed to get complete the response");
			return new ResponseEntity<ProductDetails> (productDetails, HttpStatus.OK);
		}
		return new ResponseEntity<ProductDetails>(productDetails, headers, HttpStatus.OK);
	}

	@PostMapping
	@RequestMapping("/product/{productId}/{productPrice}")
	public ProductDetails updateProduct(@PathVariable String productId, @PathVariable Double productPrice) {
		ProductDetails productDetails = null;
		//productDetails = getProduct(productId);
		if(productDetails.getProdId() != null)
		{
			currencyInterface.updateOne(productId, productPrice);
			//productDetails = getProduct(productId);
		}
		return productDetails;
	}

}
