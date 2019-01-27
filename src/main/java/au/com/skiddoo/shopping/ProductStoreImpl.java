package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
 * It provides an implementation to {@link ProductStore}.
 * 
 * @author junfeng
 */
public class ProductStoreImpl implements ProductStore {

	private static ProductStoreImpl instance;

	/**
	 * Singleton class constructor
	 */
	private ProductStoreImpl() {

	}

	/**
	 * Get a singleton instance using lazy initialization.
	 * 
	 * @return a singleton instance
	 */
	public static ProductStoreImpl getInstance() {
		if (instance == null) {
			instance = new ProductStoreImpl();
		}

		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product getProduct(String productSku) {
		SKU sku = null;
		try {
			sku = SKU.valueOf(productSku);
		} catch (IllegalArgumentException | NullPointerException exception) {
			sku = SKU.UNKNOWN;
		}

		return getProduct(sku);
	}

	/**
	 * Get a {@link Product} matching the given {@link SKU}.
	 * 
	 * @param sku
	 *            used to check against the store category
	 * @return a {@link Product} matching the given {@link SKU}
	 */
	private Product getProduct(SKU sku) {
		Product result = null;
		switch (sku) {
			case IPAD:
				result = new Product(sku.getLabel(), Constants.IPAD_NAME, new Price(Constants.IPAD_PRICE));
				break;
			case APPLE_TV:
				result = new Product(sku.getLabel(), Constants.APPLE_TV_NAME, new Price(Constants.APPLE_TV_PRICE));
				break;
			case MACBOOK_PRO:
				result = new Product(sku.getLabel(), Constants.MACBOOK_PRO_NAME,
						new Price(Constants.MACBOOK_PRO_PRICE));
				break;
			case HDMI_ADAPTER:
				result = new Product(sku.getLabel(), Constants.HDMI_ADAPTER_NAME,
						new Price(Constants.HDMI_ADAPTER_PRICE));
				break;
			default:
				result = new Product(SKU.UNKNOWN.getLabel(), Constants.UNKNOWN_NAME,
						new Price(Constants.UNKNOWN_PRICE));
				break;
		}

		return result;
	}
}
