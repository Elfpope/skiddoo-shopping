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
 * Test {@link IpadPricingRule}
 * 
 * @author junfeng
 */
public class TestIpadPricingRule {

	private CheckoutRegister register;

	@Before
	public void setup() {
		List<PricingRule> rules = Arrays.asList(IpadPricingRule.getInstance());
		ProductStore store = ProductStoreImpl.getInstance();
		register = new CheckoutRegisterImpl(rules, store);
	}

	/**
	 * Test {@link IpadPricingRule#getAdjustment(List)}
	 * <p>
	 * Cover scenarios where the purchase (6 iPads) is eligible for discount.
	 * </p>
	 */
	@Test
	public void testGetAdjustment_whenEligibleForDiscount() {
		int numOfIpads = Constants.NUM_OF_PURCHASE_FOR_IPAD_DEAL + 2;
		IntStream.range(0, numOfIpads).forEach(i -> register.read(SKU.IPAD.name()));

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.DISCOUNTED_IPAD_PRICE * numOfIpads;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}

	/**
	 * Test {@link IpadPricingRule#getAdjustment(List)}
	 * <p>
	 * Cover scenarios where purchases are NOT eligible for discount:
	 * <ul>
	 * <li>there is 0 ipad in the purchase</li>
	 * <li>there is 1 ipad in the purchase</li>
	 * <li>there are 2 ipads in the purchase</li>
	 * <li>there are 3 ipads in the purchase</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetAdjustment_whenIneligibleForDiscount() {
		// there is 0 ipad in the purchase
		register.read(SKU.APPLE_TV.name());
		register.read(SKU.MACBOOK_PRO.name());

		double actualTotal = register.total().getAmount();
		double expectedTotal = Constants.APPLE_TV_PRICE + Constants.MACBOOK_PRO_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// there is 1 ipad in the purchase
		register.read(SKU.IPAD.name());
		register.read(SKU.APPLE_TV.name());
		register.read(SKU.HDMI_ADAPTER.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.IPAD_PRICE + Constants.APPLE_TV_PRICE + Constants.HDMI_ADAPTER_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// there are 2 ipads in the purchase
		register.read(SKU.IPAD.name());
		register.read(SKU.IPAD.name());
		register.read(SKU.MACBOOK_PRO.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.IPAD_PRICE * 2 + Constants.MACBOOK_PRO_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);

		// there are 3 ipads in the purchase
		register.read(SKU.IPAD.name());
		register.read(SKU.IPAD.name());
		register.read(SKU.IPAD.name());
		register.read(SKU.APPLE_TV.name());

		actualTotal = register.total().getAmount();
		expectedTotal = Constants.IPAD_PRICE * 3 + Constants.APPLE_TV_PRICE;
		Assert.assertEquals(expectedTotal, actualTotal, Constants.Max_DELTA);
	}
}
