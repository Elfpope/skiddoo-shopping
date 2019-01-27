package au.com.skiddoo.shopping;

import java.util.ArrayList;
import java.util.List;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.pricing.PricingRule;

/**
 * It provides an implementation to {@link CheckoutRegister}.
 * 
 * @author junfeng
 */
public class CheckoutRegisterImpl implements CheckoutRegister {

	private ProductStore products;

	private List<PricingRule> pricingRules;

	private List<Product> scannedProducts;

	/**
	 * Constructor with parameters.
	 */
	public CheckoutRegisterImpl(List<PricingRule> pricingRules, ProductStore products) {
		this.pricingRules = pricingRules;
		this.products = products;
		scannedProducts = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(String sku) {
		Product product = products.getProduct(sku);
		if (product != null) {
			scannedProducts.add(product);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price total() {
		double preAdjustedTotal = scannedProducts.stream().mapToDouble(product -> product.getPrice().getAmount()).sum();

		double adjustments = pricingRules.stream()
				.mapToDouble(rule -> rule.getAdjustment(scannedProducts).getAmount())
				.sum();

		// clear the register once all scanned products are summed up
		scannedProducts.clear();

		return new Price(preAdjustedTotal + adjustments);
	}
}
