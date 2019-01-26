package au.com.skiddoo.shopping.model;

/**
 * @author junfeng
 */
public enum SKU {
	IPAD("ipad"),
	MACBOOK_PRO("macbookpro"),
	APPLE_TV("appletv"),
	HDMI_ADAPTER("hdmiadapter");

	private final String label;

	private SKU(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
