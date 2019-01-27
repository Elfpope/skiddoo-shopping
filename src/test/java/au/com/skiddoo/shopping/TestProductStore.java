package au.com.skiddoo.shopping;

import org.junit.Assert;
import org.junit.Test;

import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * Test {@link ProductStore}
 * 
 * @author junfeng
 */
public class TestProductStore {

	private ProductStore store = ProductStoreImpl.getInstance();

	/**
	 * Test {@link ProductStore#getProduct(String)}
	 * <p>
	 * Cover below scenarios:
	 * <ul>
	 * <li>Input known sku (Ipad, Macbook Pro)</li>
	 * <li>Input unknown sku</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetProduct() {
		// Input known sku (Ipad, Macbook Pro)
		Product product = store.getProduct(SKU.APPLE_TV.name());
		Assert.assertEquals(product.getSku(), SKU.APPLE_TV.getLabel());
		Assert.assertEquals(product.getName(), Constants.APPLE_TV_NAME);
		Assert.assertEquals(product.getPrice().getAmount(), Constants.APPLE_TV_PRICE, Constants.Max_DELTA);

		product = store.getProduct(SKU.MACBOOK_PRO.name());
		Assert.assertEquals(product.getSku(), SKU.MACBOOK_PRO.getLabel());
		Assert.assertEquals(product.getName(), Constants.MACBOOK_PRO_NAME);
		Assert.assertEquals(product.getPrice().getAmount(), Constants.MACBOOK_PRO_PRICE, Constants.Max_DELTA);

		// Input unknown sku
		product = store.getProduct("blablabla");
		Assert.assertEquals(product.getSku(), SKU.UNKNOWN.getLabel());
		Assert.assertEquals(product.getName(), Constants.UNKNOWN_NAME);
		Assert.assertEquals(product.getPrice().getAmount(), Constants.UNKNOWN_PRICE, Constants.Max_DELTA);

		product = store.getProduct(null);
		Assert.assertEquals(product.getSku(), SKU.UNKNOWN.getLabel());
		Assert.assertEquals(product.getName(), Constants.UNKNOWN_NAME);
		Assert.assertEquals(product.getPrice().getAmount(), Constants.UNKNOWN_PRICE, Constants.Max_DELTA);
	}
}
