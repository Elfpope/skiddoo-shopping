// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.model.Price;

/**
 * It represents the register in the store for checkout.
 * 
 * @author junfeng
 */
public interface CheckoutRegister {

	/**
	 * Read a sku into the register.
	 * 
	 * @param sku
	 *            to read
	 */
	void read(String sku);

	/**
	 * Calculate the total {@link Price} based on all checked products.
	 * 
	 * @return the total {@link Price} based on all checked products
	 */
	Price total();

}
