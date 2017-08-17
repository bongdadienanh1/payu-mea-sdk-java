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

/**
 * Class Details
 *
 * Additional details of the lookup data entry value.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Details extends PayUModel {
    /**
     * Amount of the subtotal of the items. **Required** if line items are specified.
     */
    private String subtotal;

    /**
     * Amount charged for shipping. 10 characters max with support for 2 decimal places.
     */
    private String shipping;

    /**
     * Amount charged for tax. 10 characters max with support for 2 decimal places.
     */
    private String tax;

    /**
     * Amount being charged for the handling fee.
     */
    private String handlingFee;

    /**
     * Amount being discounted for the shipping fee.
     */
    private String shippingDiscount;

    /**
     * Amount being charged as gift wrap fee.
     */
    private String giftWrap;

    /**
     * Fee charged by PayU. In case of a refund, this is the fee amount refunded to the original receipient of the payment.
     */
    private String fee;

    /**
     * Default Constructor
     */
    public Details() {
    }
}
