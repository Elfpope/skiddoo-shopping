// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

/**
 * @author jaiew
 */
public interface CheckoutRegister {

    void read(String sku);

    Price total();

}
