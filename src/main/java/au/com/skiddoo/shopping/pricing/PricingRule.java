package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.util.Constants;

/**
 * @author junfeng
 */
public interface PricingRule {

	Price getAdjustment(final List<Product> scannedProducts);

	default int getAdjustmentFactor() {
		return Constants.NEGATIVE_ADJUSTMENT_FACTOR;
	}

}
