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
 * Class PaymentMethod
 *
 * A payment card that can fund a payment.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentMethod extends PayUModel {
    public static final String TYPE_CREDITCARD = "CREDITCARD";
    public static final String TYPE_DEBIT_ORDER = "DEBIT_ORDER";
    public static final String TYPE_EFT_PRO = "EFT_PRO";
    public static final String TYPE_SMARTEFT = "SMARTEFT";
    public static final String TYPE_EBUCKS = "EBUCKS";
    public static final String TYPE_CREDITCARD_TOKEN = "CREDITCARD_TOKEN";
    public static final String TYPE_DISCOVERYMILES = "DISCOVERYMILES";
    public static final String TYPE_REAL_TIME_RECURRING = "REAL_TIME_RECURRING";

    /**
     * The payment method id. This is in the form of a token
     */
    private String id;
    /**
     * The card number.
     */
    private String cardNumber;
    /**
     * The expiry date for the card.
     */
    private String cardExpiry;
    /**
     * The validation code for the card.
     */
    private String cvv;
    /**
     * The card type.
     * Valid Values: ["VISA", "MASTERCARD"]
     */
    private String information;
    /**
     * Payment amount in integer
     */
    private int amountInCents;
    /**
     * The full name of the card holder.
     */
    private String nameOnCard;
    /**
     * The verified status of the payment method.
     */
    private String verified;
    /**
     * The payment method description set by the user
     */
    private String description;
    /**
     * The payment method ID
     */
    private String pmId;
    /**
     * The default payment method
     */
    private String defaultPM;
    /**
     * EFT funding instrument reference
     */
    private String reference;
    /**
     * eBucks funding instrument token
     */
    private String ebucksToken;
}
