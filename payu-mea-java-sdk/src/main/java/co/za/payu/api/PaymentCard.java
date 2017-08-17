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
 * Class PaymentCard
 *
 * A payment card that can fund a payment.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentCard extends PayUModel {
    public static final String TYPE_VISA = "VISA";
    public static final String TYPE_MASTERCARD = "MASTERCARD";
    public static final String TYPE_MAESTRO = "MAESTRO";
    public static final String TYPE_DISCOVERYMILES = "DISCOVERYMILES";

    /**
     * The ID of a credit card to save for later use.
     */
    private String id;

    /**
     * The card number.
     */
    private String number;

    /**
     * The card type.
     */
    private String type;

    /**
     * The two-digit expiry month for the card.
     */
    private String expireMonth;

    /**
     * The four-digit expiry year for the card.
     */
    private String expireYear;

    /**
     * The two-digit start month for the card. Required for UK Maestro cards.
     */
    private String startMonth;

    /**
     * The four-digit start year for the card. Required for UK Maestro cards.
     */
    private String startYear;

    /**
     * The validation code for the card. Supported for payments but not for saving payment cards for future use.
     */
    private String cvv2;

    /**
     * The first name of the card holder.
     */
    private String firstName;

    /**
     * The last name of the card holder.
     */
    private String lastName;

    /**
     * The two-letter country code.
     */
    private String billingCountry;

    /**
     * The billing address for the card.
     */
    private Address billingAddress;

    /**
     * The state of the funding instrument.
     */
    private String status;

    /**
     * The one- to two-digit card issue number. Required for UK Maestro cards.
     */
    private String issueNumber;

    /**
     * Fields required to support budget payments when processing credit card payments. Only supported when the `payment_method` is set to `credit_card`.
     */
    private boolean showBudget;

    /**
     * Fields required to support 3d secure information when processing credit card payments. Only supported when the `payment_method` is set to `credit_card`.
     */
    private boolean secure3D;

    /**
     * Default Constructor
     */
    public PaymentCard() {
    }
}
