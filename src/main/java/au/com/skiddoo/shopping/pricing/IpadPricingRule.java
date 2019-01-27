package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * It provides an implementation for the {@link PricingRule} associated with the Ipad.
 * 
 * @author junfeng
 */
public class IpadPricingRule implements PricingRule {

	private static IpadPricingRule instance;

	/**
	 * Singleton class constructor
	 */
	private IpadPricingRule() {
	}

	/**
	 * Get a singleton instance using lazy initialization.
	 * 
	 * @return a singleton instance
	 */
	public static IpadPricingRule getInstance() {
		if (instance == null) {
			instance = new IpadPricingRule();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price getAdjustment(List<Product> scannedProducts) {
		if (scannedProducts == null || scannedProducts.isEmpty()) {
			return new Price(0);
		}

		double adjustment = 0;
		long numOfIpads = scannedProducts.stream().filter(this::isIpad).count();
		if (numOfIpads >= Constants.NUM_OF_PURCHASE_FOR_IPAD_DEAL) {
			Product ipad = scannedProducts.stream().filter(this::isIpad).findFirst().get();
			double adjustmentUnit = ipad.getPrice().getAmount() - Constants.DISCOUNTED_IPAD_PRICE;

			adjustment = adjustmentUnit * numOfIpads * getAdjustmentFactor();
		}

		return new Price(adjustment);
	}

	/**
	 * Check if the given {@link Product} is an Ipad.
	 * 
	 * @param product
	 *            to check
	 * @return {@code true} if the given {@link Product} is an Ipad; otherwise {@code false}
	 */
	private boolean isIpad(Product product) {
		return SKU.IPAD.getLabel().equals(product.getSku());
	}
}
