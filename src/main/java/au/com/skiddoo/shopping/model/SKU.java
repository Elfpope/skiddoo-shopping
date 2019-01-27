package au.com.skiddoo.shopping.model;

/**
 * @author junfeng
 */
public enum SKU {
	IPAD("ipad"),
	APPLE_TV("appletv"),
	MACBOOK_PRO("macbookpro"),
	HDMI_ADAPTER("hdmiadapter"),
	UNKNOWN("unknown");

	private final String label;

	private SKU(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
