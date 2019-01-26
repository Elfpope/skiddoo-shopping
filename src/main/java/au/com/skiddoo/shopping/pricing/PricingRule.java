package au.com.skiddoo.shopping.pricing;

import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;

/**
 * @author junfeng
 */
public interface PricingRule {

	Price getAdjustment(final List<Product> scannedProducts);

	int getAdjustmentFactor();

}
