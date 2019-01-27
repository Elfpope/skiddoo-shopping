package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * @author junfeng
 */
public class AppleTvPricingRule implements PricingRule {

	private static AppleTvPricingRule instance;

	/**
	 * Singleton class constructor
	 */
	private AppleTvPricingRule() {

	}

	/**
	 * Get a singleton instance using lazy initialization.
	 * 
	 * @return
	 */
	public static AppleTvPricingRule getInstance() {
		if (instance == null) {
			instance = new AppleTvPricingRule();
		}

		return instance;
	}

	@Override
	public Price getAdjustment(List<Product> scannedProducts) {
		if (scannedProducts == null || scannedProducts.isEmpty()) {
			return new Price(0);
		}

		double adjustment = 0;
		long numOfAppleTv = scannedProducts.stream().filter(this::isAppleTv).count();
		if (numOfAppleTv >= Constants.NUM_OF_PURCHASE_FOR_APPLE_TV_DEAL) {
			Product appleTv = scannedProducts.stream().filter(this::isAppleTv).findFirst().get();
			double adjustmentUnit = appleTv.getPrice().getAmount();

			long numOfAdjustment = numOfAppleTv / Constants.NUM_OF_PURCHASE_FOR_APPLE_TV_DEAL;
			adjustment = adjustmentUnit * numOfAdjustment * getAdjustmentFactor();
		}

		return new Price(adjustment);
	}

	private boolean isAppleTv(Product product) {
		return SKU.APPLE_TV.getLabel().equals(product.getSku());
	}
}
