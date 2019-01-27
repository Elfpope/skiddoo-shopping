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
 * Test {@link MacbookPricingRule}
 * 
 * @author junfeng
 */
public class TestMacbookPricingRule {

	private CheckoutRegister register;

	/**
	 * Set up the {@link CheckoutRegister} with {@link MacbookPricingRule} before any test.
	 */
	@Before
	public void setup() {
		List<PricingRule> rules = Arrays.asList(MacbookPricingRule.getInstance());
		ProductStore store = ProductStoreImpl.getInstance();
		register = new CheckoutRegisterImpl(rules, store);
	}

	/**
	 * Test {@link MacbookPricingRule#getAdjustment(List)}
	 * <p>
	 * Cover scenarios where purchases are eligible for discount:
	 * <ul>
	 * <li>get 1 Macbook deal (1 Macbook plus a HDMI adapter)</li>
	 * <li>get more than 1 Macbook deals (3 Macbooks plus 4 HDMI adapters)</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetAdjustment_whenEligibleForDiscount() {
		// get 1 Macbook deal (1 Macbook plus a HDMI adapter)
		register.read(SKU.MACBOOK_PRO.name());
		register.read(SKU.HDMI_ADAPTER.name());

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.MACBOOK_PRO_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// get more than 1 Macbook deals (3 Macbooks plus 4 HDMI adapters)
		IntStream.range(0, 3).forEach(i -> register.read(SKU.MACBOOK_PRO.name()));
		IntStream.range(0, 4).forEach(i -> register.read(SKU.HDMI_ADAPTER.name()));

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.MACBOOK_PRO_PRICE * 3 + Constants.HDMI_ADAPTER_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}

	/**
	 * Test {@link MacbookPricingRule#getAdjustment(List)}
	 * <p>
	 * Cover scenarios where purchases are NOT eligible for discount:
	 * <ul>
	 * <li>there is 1 Macbook in the purchase but no HDMI adapter</li>
	 * <li>there is 1 HDMI adapter in the purchase but no Macbook</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetAdjustment_whenIneligibleForDiscount() {
		// there is 1 Macbook in the purchase but no HDMI adapter
		register.read(SKU.APPLE_TV.name());
		register.read(SKU.MACBOOK_PRO.name());

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.APPLE_TV_PRICE + Constants.MACBOOK_PRO_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// there is 1 HDMI adapter in the purchase but no Macbook
		register.read(SKU.APPLE_TV.name());
		register.read(SKU.HDMI_ADAPTER.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.APPLE_TV_PRICE + Constants.HDMI_ADAPTER_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}
}
