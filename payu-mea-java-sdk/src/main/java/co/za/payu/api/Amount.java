/**
 * PayU MEA JAVA SDK
 *
 * @copyright  Copyright (c) 2016 PayU
 * @license    http://opensource.org/licenses/LGPL-3.0  Open Software License (LGPL 3.0)
 * @link http://www.payu.co.za
 * @link http://help.payu.co.za/developers
 * @author Kenneth Onah <kenneth@netcraft-devops.com>
 */
package co.za.payu.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import co.za.payu.base.soap.PayUModel;

/**
 * Class Amount
 *
 * Payment amount.
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Amount extends PayUModel {

    /**
     * 3-letter [currency code]. PayU supports ZAR, NGN currencies only.
     */
    private String currency;

    /**
     * Total amount charged from the customer to the merchant. In case of
     * a refund, this is the refunded amount to the original customer from
     * the merchant. 10 characters max with support for 2 decimal places.
     */
    private String total;

    /**
     * Additional details of the payment amount.
     */
    private Details details;

    /**
     * Default Constructor
     */
    public Amount() {
    }

    /**
     * Parameterized Constructor
     */
    public Amount(String currency, String total) {
        this.currency = currency;
        this.total = total;
    }
}
