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
 * Class Basket
 *
 * Basket class contains summary of the cart
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Basket extends PayUModel {
    /**
     * 3-letter [currency code]. PayU MEA supports ZAR, NGN currencies only.
     */
    private String amountInCents;

    /**
     * Total amount charged from the customer to the merchant. In case of
     * a refund, this is the refunded amount to the original customer from
     * the merchant. 10 characters max with support for 2 decimal places.
     */
    private String currencyCode;

    /**
     * Additional details of the payment amount.
     */
    private Details description;
}
