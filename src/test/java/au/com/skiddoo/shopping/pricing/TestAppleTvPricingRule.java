package au.com.skiddoo.shopping.pricing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.skiddoo.shopping.CheckoutRegister;
import au.com.skiddoo.shopping.CheckoutRegisterImpl;
import au.com.skiddoo.shopping.ProductStore;
import au.com.skiddoo.shopping.ProductStoreImpl;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * Test {@link AppleTvPricingRule}
 * 
 * @author junfeng
 */
public class TestAppleTvPricingRule {

	private CheckoutRegister register;

	/**
	 * Set up the {@link CheckoutRegister} with {@link AppleTvPricingRule} before any test.
	 */
	@Before
	public void setup() {
		List<PricingRule> rules = Arrays.asList(AppleTvPricingRule.getInstance());
		ProductStore store = ProductStoreImpl.getInstance();
		register = new CheckoutRegisterImpl(rules, store);
	}

	/**
	 * Test {@link AppleTvPricingRule#getAdjustment(List)}
	 * <p>
	 * Cover scenarios where purchases are eligible for discount:
	 * <ul>
	 * <li>get 1 Apple TV deal (3 Apple TVs)</li>
	 * <li>get 2 Apple TV deals with another product (7 Apple TVs plus an Ipad)</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetAdjustment_whenEligibleForDiscount() {

		// get 1 Apple TV deal (3 Apple TVs)
		IntStream.range(0, Constants.NUM_OF_PURCHASE_FOR_APPLE_TV_DEAL)
				.forEach(i -> register.read(SKU.APPLE_TV.name()));

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.APPLE_TV_PRICE * 2;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// get 2 Apple TV deals with another product (7 Apple TVs plus an Ipad)
		IntStream.range(0, Constants.NUM_OF_PURCHASE_FOR_APPLE_TV_DEAL * 2 + 1)
				.forEach(i -> register.read(SKU.APPLE_TV.name()));
		register.read(SKU.IPAD.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.APPLE_TV_PRICE * 5 + Constants.IPAD_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}

	/**
	 * Test {@link AppleTvPricingRule#getAdjustment(List)}
	 * <p>
	 * Cover scenarios where purchases are NOT eligible for discount:
	 * <ul>
	 * <li>there is 0 Apple TV in the purchase</li>
	 * <li>there is 1 Apple TV in the purchase</li>
	 * <li>there are 2 Apple TVs in the purchase</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetAdjustment_whenIneligibleForDiscount() {
		// there is 0 Apple TV in the purchase
		register.read(SKU.IPAD.name());
		register.read(SKU.MACBOOK_PRO.name());

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.IPAD_PRICE + Constants.MACBOOK_PRO_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// there is 1 Apple TV in the purchase
		register.read(SKU.IPAD.name());
		register.read(SKU.APPLE_TV.name());
		register.read(SKU.HDMI_ADAPTER.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.IPAD_PRICE + Constants.APPLE_TV_PRICE + Constants.HDMI_ADAPTER_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// there are 2 Apple TVs in the purchase
		register.read(SKU.IPAD.name());
		register.read(SKU.APPLE_TV.name());
		register.read(SKU.APPLE_TV.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.IPAD_PRICE + Constants.APPLE_TV_PRICE * 2;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}
}
