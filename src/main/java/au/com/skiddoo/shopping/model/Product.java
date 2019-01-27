// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping.model;

/**
 * Model class represents a product in the store category
 * 
 * @author junfeng
 */
public class Product {

	private final String sku;
	private final String name;
	private final Price price;

	/**
	 * Constructor with parameters
	 */
	public Product(String sku, String name, Price price) {
		this.sku = sku;
		this.name = name;
		this.price = price;
	}

	/**
	 * Get {@link #sku} of the product.
	 * 
	 * @return {@link #sku} of the product
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * Get {@link #name} of the product.
	 * 
	 * @return {@link #name} of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get {@link #price} of the product.
	 * 
	 * @return Get {@link #price} of the product
	 */
	public Price getPrice() {
		return price;
	}
}
