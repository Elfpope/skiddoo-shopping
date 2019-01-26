package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * @author junfeng
 */
public class IpadPricingRule implements PricingRule {

	private static final int NUM_OF_PURCHASE_FOR_A_DEAL = 4;

	private static final double DISCOUNTED_IPAD_PRICE = 499.99;

	@Override
	public Price getAdjustment(List<Product> scannedProducts) {
		if (scannedProducts == null || scannedProducts.isEmpty()) {
			return new Price(0);
		}

		double adjustment = 0;
		long numOfIpads = scannedProducts.stream().filter(this::isIpad).count();
		if (numOfIpads >= NUM_OF_PURCHASE_FOR_A_DEAL) {
			Product ipad = scannedProducts.stream().filter(this::isIpad).findFirst().get();
			double adjustmentUnit = ipad.getPrice().getAmount() - DISCOUNTED_IPAD_PRICE;

			adjustment = adjustmentUnit * numOfIpads * getAdjustmentFactor();
		}

		return new Price(adjustment);
	}

	@Override
	public int getAdjustmentFactor() {
		return Constants.NEGATIVE_ADJUSTMENT_FACTOR;
	}

	private boolean isIpad(Product product) {
		return SKU.IPAD.getLabel().equals(product.getSku());
	}
}
