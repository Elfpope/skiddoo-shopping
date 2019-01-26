package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * @author junfeng
 */
public class MacbookPricingRule implements PricingRule {

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

	@Override
	public int getAdjustmentFactor() {
		return Constants.NEGATIVE_ADJUSTMENT_FACTOR;
	}

	private boolean isMacbookPro(Product product) {
		return SKU.MACBOOK_PRO.getLabel().equals(product.getSku());
	}

	private boolean isHdmiAdapter(Product product) {
		return SKU.HDMI_ADAPTER.getLabel().equals(product.getSku());
	}
}
