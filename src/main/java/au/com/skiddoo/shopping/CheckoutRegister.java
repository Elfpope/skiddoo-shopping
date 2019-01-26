// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.model.Price;

/**
 * @author junfeng
 */
public interface CheckoutRegister {

	void read(String sku);

	Price total();

}
