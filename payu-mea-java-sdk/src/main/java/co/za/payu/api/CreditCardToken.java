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
 * Class CreditCardToken
 *
 * A resource representing a credit card that can be used to fund a payment.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditCardToken extends PayUModel {
    /**
     * ID of credit card previously stored using.
     */
    private String creditCardId;

    /**
     * Last four digits of the stored credit card number.
     */
    private String last4;

    /**
     * Credit card type. Valid types are: `VISA`, `MASTERCARD`, `DISCOVERYMILES`.
     */
    private String type;

    /**
     * The validation code for the card. Supported for payments but not for saving payment cards for future use.
     */
    private String cvv2;

    /**
     * Expiration month with no leading zero. Acceptable values are 1 through 12.
     */
    private int expireMonth;

    /**
     * 4-digit expiration year.
     */
    private int expireYear;

    /**
     * Parameterized Constructor
     */
    public CreditCardToken(String creditCardId) {
        this.creditCardId = creditCardId;
    }
}
