// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.model.Product;

/**
 * It represents the store category.
 * 
 * @author junfeng
 */
public interface ProductStore {

	/**
	 * Get a {@link Product} matching the given product SKU.
	 * 
	 * @param productSku
	 *            to check against the store category
	 * @return a {@link Product} matching the given product SKU
	 */
	Product getProduct(String productSku);
}
