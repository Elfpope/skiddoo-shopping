// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping.model;

/**
 * Model class represents a price.
 * 
 * @author junfeng
 */
public class Price {

	private final double amount;

	/**
	 * Constructor with a parameter
	 */
	public Price(double amount) {
		this.amount = amount;
	}

	/**
	 * Get the amount of the price.
	 * 
	 * @return the amount of the price
	 */
	public double getAmount() {
		return amount;
	}
}
