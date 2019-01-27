package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.model.Price;
import au.com.skiddoo.shopping.model.Product;
import au.com.skiddoo.shopping.model.SKU;
import au.com.skiddoo.shopping.util.Constants;

/**
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
	 * @return
	 */
	public static ProductStoreImpl getInstance() {
		if (instance == null) {
			instance = new ProductStoreImpl();
		}

		return instance;
	}

	@Override
	public Product getProduct(String productSku) {
		SKU sku = null;
		try {
			sku = SKU.valueOf(productSku);
		} catch (IllegalArgumentException exception) {
			sku = SKU.UNKNOWN;
		}

		return getProduct(sku);
	}

	public Product getProduct(SKU sku) {
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
