package com.deepakrohan.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ResponseEntity getProduct(@PathVariable String productId) {
		ProductDetails productDetails = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		try {
			ProductDataBO productData = restTemplate.getForObject(redskyUrl, ProductDataBO.class, productId);
			productDetails = currencyInterface.findOne(productData);
		}catch(NullPointerException ex) {
			logger.error("No such object found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}catch(HttpClientErrorException ex) {
			logger.error("failed to get complete the response");
			return ResponseEntity.status(HttpStatus.OK).body(new ProductDetails(null, null, null, null));
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDetails);
	}


	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.PUT)

	public ResponseEntity updateProduct(@PathVariable String productId, @Valid @RequestBody Double productPrice ) {
		ProductDetails productDetails = (ProductDetails) getProduct(productId).getBody();
		System.out.println(productPrice);
		if(productDetails.getProdId() != null)
		{

			Boolean updateSuccess = currencyInterface.updateOne(productId, productPrice);
			productDetails = (ProductDetails) getProduct(productId).getBody();


		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ProductDetails(null, null, null, null));

		}
		return ResponseEntity.status(HttpStatus.OK).body(productDetails);
	}
}

