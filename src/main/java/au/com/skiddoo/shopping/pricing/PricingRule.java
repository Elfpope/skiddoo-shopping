package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.util.Constants;

/**
 * It represents a pricing rule associated to a {@link Product}.
 * 
 * @author junfeng
 */
public interface PricingRule {

	/**
	 * Calculate the adjustment based on the given list of {@link Product}s.
	 * 
	 * @param products
	 *            to be examined
	 * @return the adjustment
	 */
	Price getAdjustment(final List<Product> products);

	/**
	 * Get the adjustment factor indicating how the adjustment applies to the total original {@link Price} of all
	 * checkout {@link Product}s.
	 * 
	 * @return the adjustment factor
	 */
	default int getAdjustmentFactor() {
		return Constants.NEGATIVE_ADJUSTMENT_FACTOR;
	}
}
