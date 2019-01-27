package au.com.skiddoo.shopping.model;

/**
 * It maps to all known SKU values to apply strong-type to SKU values.
 * 
 * @author junfeng
 */
public enum SKU {
	IPAD("ipad"),
	APPLE_TV("appletv"),
	MACBOOK_PRO("macbookpro"),
	HDMI_ADAPTER("hdmiadapter"),
	UNKNOWN("unknown");

	private final String label;

	/**
	 * Constructor with a parameter.
	 * 
	 * @param label
	 */
	private SKU(String label) {
		this.label = label;
	}

	/**
	 * Get the sku label which is used in store category.
	 * 
	 * @return the sku label which is used in store category
	 */
	public String getLabel() {
		return label;
	}
}
