/**
 * PayU MEA PHP SDK
 *
 * @copyright  Copyright (c) 2016 PayU
 * @license    http://opensource.org/licenses/LGPL-3.0  Open Software License (LGPL 3.0)
 * @link       http://www.payu.co.za
 * @link       http://help.payu.co.za/developers
 * @author     Kenneth Onah <kenneth@netcraft-devops.com>
 */
package co.za.payu.api;

import co.za.payu.base.soap.PayUModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Class ItemList
 *
 * List of items being paid for.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ItemList extends PayUModel {
    /**
     * List of items.
     */
    private List<Item> items;
    /**
     * Shipping address.
     */
    private ShippingAddress shippingAddress;
    /**
     * Shipping method used for this payment like Courier Guy etc.
     */
    private String shippingMethod;
    /**
     * Allows merchant's to share customer’s contact number with PayU for the current payment.
     * The phone number must be represented in its canonical international format, as defined by the E.164 numbering plan
     */
    private String shippingPhoneNumber;

    /**
     * Default Constructor
     */
    public ItemList() {
    }
}
