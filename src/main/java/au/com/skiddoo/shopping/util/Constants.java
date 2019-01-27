package au.com.skiddoo.shopping.util;

/**
 * @author junfeng
 */
public abstract class Constants {

	public static final String PRICE_FORMAT_PATTERN = "$%.02f";

	public static final int NEGATIVE_ADJUSTMENT_FACTOR = -1;

	/**
	 * the maximum delta between 2 double values for which both doubles are still considered equal
	 */
	public static final double Max_DELTA = 0.001d;

	// Product Properties
	public static final String IPAD_NAME = "Super iPad";
	public static final double IPAD_PRICE = 649.99;

	public static final String APPLE_TV_NAME = "Apple TV";
	public static final double APPLE_TV_PRICE = 209.50;

	public static final String MACBOOK_PRO_NAME = "MacBook Pro";
	public static final double MACBOOK_PRO_PRICE = 1499.99;

	public static final String HDMI_ADAPTER_NAME = "HDMI adapter";
	public static final double HDMI_ADAPTER_PRICE = 130.00;

	public static final String UNKNOWN_NAME = "Unknown Product";
	public static final double UNKNOWN_PRICE = 0.00;

	// Product Promotion
	public static final int NUM_OF_PURCHASE_FOR_APPLE_TV_DEAL = 3;

	public static final int NUM_OF_PURCHASE_FOR_IPAD_DEAL = 4;
	public static final double DISCOUNTED_IPAD_PRICE = 499.99;
}
