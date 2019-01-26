package au.com.skiddoo.shopping;

import java.util.HashMap;
import java.util.Map;

import au.com.skiddoo.shopping.model.Product;

/**
 * @author junfeng
 */
public class ProductStoreImpl implements ProductStore {

	private Map<String, Product> productCategory = new HashMap<String, Product>();

	@Override
	public Product getProduct(String productSku) {
		boolean nonEmptySku = productSku != null && !productSku.isEmpty();
		return nonEmptySku ? productCategory.get(productSku) : null;
	}

}
