// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.pricing.AppleTvPricingRule;
import au.com.skiddoo.shopping.pricing.IpadPricingRule;
import au.com.skiddoo.shopping.pricing.MacbookPricingRule;
import au.com.skiddoo.shopping.pricing.PricingRule;
import au.com.skiddoo.shopping.util.Constants;

/**
 * Integration Test on {@link CheckoutRegister}
 * 
 * @author junfeng
 */
public class CheckoutRegisterTests {

	private CheckoutRegister register;

	/**
	 * Set up the {@link CheckoutRegister} with all available {@link PricingRule}s before any test.
	 */
	@Before
	public void setup() {
		List<PricingRule> rules = Arrays.asList(AppleTvPricingRule.getInstance(), IpadPricingRule.getInstance(),
				MacbookPricingRule.getInstance());
		ProductStore store = ProductStoreImpl.getInstance();

		register = new CheckoutRegisterImpl(rules, store);
	}

	/**
	 * Test {@link CheckoutRegister#total()}
	 * <p>
	 * Cover below scenarios:
	 * <ul>
	 * <li>Read SKU: appletv, appletv, appletv, hdmiadapter</li>
	 * <li>Read SKU: appletv, ipad, ipad, appletv, ipad, ipad, ipad</li>
	 * <li>Read SKU: macbookpro, hdmiadapter, ipad</li>
	 * <li>Read SKU: 5 * macbookpro , 8 * hdmiadapter, 6 * ipad, 10 * appletv</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testTotal() {
		// Read SKU: appletv, appletv, appletv, hdmiadapter
		register.read(SKU.HDMI_ADAPTER.name());
		IntStream.range(0, 3).forEach(i -> register.read(SKU.APPLE_TV.name()));

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.APPLE_TV_PRICE * 2 + Constants.HDMI_ADAPTER_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// Read SKU: appletv, ipad, ipad, appletv, ipad, ipad, ipad
		IntStream.range(0, 2).forEach(i -> register.read(SKU.APPLE_TV.name()));
		IntStream.range(0, 5).forEach(i -> register.read(SKU.IPAD.name()));

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.APPLE_TV_PRICE * 2 + Constants.DISCOUNTED_IPAD_PRICE * 5;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// Read SKU: macbookpro, hdmiadapter, ipad
		register.read(SKU.MACBOOK_PRO.name());
		register.read(SKU.HDMI_ADAPTER.name());
		register.read(SKU.IPAD.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.MACBOOK_PRO_PRICE + Constants.IPAD_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// Read SKU: 6 * ipad, 7 * appletv, 8 * macbookpro , 9 * hdmiadapter,
		IntStream.range(0, 6).forEach(i -> register.read(SKU.IPAD.name()));
		IntStream.range(0, 7).forEach(i -> register.read(SKU.APPLE_TV.name()));
		IntStream.range(0, 8).forEach(i -> register.read(SKU.MACBOOK_PRO.name()));
		IntStream.range(0, 9).forEach(i -> register.read(SKU.HDMI_ADAPTER.name()));

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.DISCOUNTED_IPAD_PRICE * 6 + Constants.APPLE_TV_PRICE * 5
				+ Constants.MACBOOK_PRO_PRICE * 8 + Constants.HDMI_ADAPTER_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}
}
