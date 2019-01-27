package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;

/**
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
	 * @return
	 */
	public static MacbookPricingRule getInstance() {
		if (instance == null) {
			instance = new MacbookPricingRule();
		}
		return instance;
	}

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

	private boolean isMacbookPro(Product product) {
		return SKU.MACBOOK_PRO.getLabel().equals(product.getSku());
	}

	private boolean isHdmiAdapter(Product product) {
		return SKU.HDMI_ADAPTER.getLabel().equals(product.getSku());
	}
}
