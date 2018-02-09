package com.deepakrohan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"item"
})
public class Product {

@JsonProperty("item")
private Item item;

@JsonProperty("item")
public Item getItem() {
return item;
}

@JsonProperty("item")
public void setItem(Item item) {
this.item = item;
}

@Override
public String toString() {
	return "Product [item=" + item + "]";
}



}
