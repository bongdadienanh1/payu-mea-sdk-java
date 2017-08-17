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
 * Class Currency
 *
 * Base object for all financial value related fields (balance, payment due, etc.)
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Currency extends PayUModel {
    /**
     * 3 letter currency code as defined by ISO 4217.
     */
    private String currency;

    /**
     * amount up to N digit after the decimals separator as defined in ISO 4217 for the appropriate currency code.
     */
    private String value;

    /**
     * Default Constructor
     */
    public Currency() {
    }

    /**
     * Parameterized Constructor
     */
    public Currency(String currency, String value) {
        this.currency = currency;
        this.value = value;
    }
}
