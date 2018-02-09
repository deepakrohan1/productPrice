package com.deepakrohan.service;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.deepakrohan.model.ProductDataBO;
import com.deepakrohan.model.ProductDetails;
import com.deepakrohan.repo.CurrencyInterface;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
public class CurrencyService implements CurrencyInterface{
	
	
	/**
	 * Get DB Collection
	 * @return
	 */
	
	public MongoCollection<Document> getDbCollection() {
		
		MongoClientURI uri  = new MongoClientURI("mongodb://troy:iloveyou24@ds115045.mlab.com:15045/deepakrohan"); 
		MongoClient client = new MongoClient(uri);
		MongoDatabase db = client.getDatabase("deepakrohan");

		MongoCollection<Document> collection = db.getCollection("prodBase");
		return collection;
		
	}

	/**
	 * Find One method to get  the prodId
	 */
	@Override
	public ProductDetails findOne(ProductDataBO productData) {

		MongoCollection<Document> collection = getDbCollection();
		Bson filter = new Document("_id", productData.getProduct().getItem().getTcin());

		collection.find(filter);
		List<Document> all = collection.find(filter).into(new ArrayList<Document>());
		ProductDetails productDetails = null;
		
		for(Document cur:all) {
			productDetails = new ProductDetails((String) cur.get("_id"),
								(Double) cur.get("product_price"),
								(String) cur.get("product_currency"),
								(String) productData.getProduct().getItem().getProductDescription().getTitle());
			
		}

		return productDetails;

	}

	@Override
	public boolean updateOne(String productId, Double productPrice) {
		MongoCollection<Document> collection = getDbCollection();
        UpdateResult t = collection.updateOne(eq("_id",productId), new Document("$set",
        					new Document ("product_price", productPrice)));
        return t.getModifiedCount() > 0;
}
}
