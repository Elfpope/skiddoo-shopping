package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;

/**
 * It provides an implementation for the {@link PricingRule} associated with the Macbook Pro.
 * 
 * @author junfeng
 */
public class MacbookPricingRule implements PricingRule {

	private static MacbookPricingRule instance;

	/**
	 * Singleton class constructor
	 */
	private MacbookPricingRule() {
	}

	/**
	 * Get a singleton instance using lazy initialization.
	 * 
	 * @return a singleton instance
	 */
	public static MacbookPricingRule getInstance() {
		if (instance == null) {
			instance = new MacbookPricingRule();
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
		long numOfMacbooks = scannedProducts.stream().filter(this::isMacbookPro).count();
		long numOfHdmiAdapters = scannedProducts.stream().filter(this::isHdmiAdapter).count();

		if (numOfMacbooks > 0 && numOfHdmiAdapters > 0) {
			Product hdmiAdapter = scannedProducts.stream().filter(this::isHdmiAdapter).findFirst().get();
			double adjustmentUnit = hdmiAdapter.getPrice().getAmount();

			long numOfAdjustment = Math.min(numOfMacbooks, numOfHdmiAdapters);
			adjustment = adjustmentUnit * numOfAdjustment * getAdjustmentFactor();
		}

		return new Price(adjustment);
	}

	/**
	 * Check if the given {@link Product} is a Macbook Pro.
	 * 
	 * @param product
	 *            to check
	 * @return {@code true} if the given {@link Product} is a Macbook Pro; otherwise {@code false}
	 */
	private boolean isMacbookPro(Product product) {
		return SKU.MACBOOK_PRO.getLabel().equals(product.getSku());
	}

	/**
	 * Check if the given {@link Product} is a HDMI adapter.
	 * 
	 * @param product
	 *            to check
	 * @return {@code true} if the given {@link Product} is a HDMI adapter; otherwise {@code false}
	 */
	private boolean isHdmiAdapter(Product product) {
		return SKU.HDMI_ADAPTER.getLabel().equals(product.getSku());
	}
}
