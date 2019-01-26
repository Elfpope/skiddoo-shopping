// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.model.Product;

/**
 * @author junfeng
 */
public interface ProductStore {

	Product getProduct(String productSku);

}
