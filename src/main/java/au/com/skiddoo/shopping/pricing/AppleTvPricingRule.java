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

	private static final int NUM_OF_PURCHASE_FOR_A_DEAL = 3;

	@Override
	public Price getAdjustment(List<Product> scannedProducts) {
		if (scannedProducts == null || scannedProducts.isEmpty()) {
			return new Price(0);
		}

		double adjustment = 0;
		long numOfAppleTv = scannedProducts.stream().filter(this::isAppleTv).count();
		if (numOfAppleTv >= NUM_OF_PURCHASE_FOR_A_DEAL) {
			Product appleTv = scannedProducts.stream().filter(this::isAppleTv).findFirst().get();
			double adjustmentUnit = appleTv.getPrice().getAmount();

			long numOfAdjustment = numOfAppleTv % NUM_OF_PURCHASE_FOR_A_DEAL;
			adjustment = adjustmentUnit * numOfAdjustment * getAdjustmentFactor();
		}

		return new Price(adjustment);
	}

	@Override
	public int getAdjustmentFactor() {
		return Constants.NEGATIVE_ADJUSTMENT_FACTOR;
	}

	private boolean isAppleTv(Product product) {
		return SKU.APPLE_TV.getLabel().equals(product.getSku());
	}
}
