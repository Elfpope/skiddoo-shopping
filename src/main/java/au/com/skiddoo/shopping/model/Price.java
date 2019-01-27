// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping.model;

import au.com.skiddoo.shopping.util.Constants;

/**
 * @author jaiew
 */
public class Price {

	private final double amount;

	public Price(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	/**
	 * Compare with the given {@link Price} if both are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Price && toString().equals(String.valueOf(obj));
	}

	@Override
	public String toString() {
		return String.format(Constants.PRICE_FORMAT_PATTERN, amount);
	}
}
