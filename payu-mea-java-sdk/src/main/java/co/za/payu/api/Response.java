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
 * Class Response
 *
 * Response class contains response from SOAP method call
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Response extends PayUModel {
    /**
     * User friendly error display message
     */
    private String displayMessage;
    /**
     * Merchant specified transaction identifier. Maybe unique or otherwise.
     */
    private String merchantReference;
    /**
     * PayU unique transaction identifier
     */
    private String payUReference;
    /**
     * Result code of transaction
     */
    private String resultCode;
    /**
     * Result message of transaction
     */
    private String resultMessage;
    /**
     * Transaction is successful boolean flag
     */
    private boolean successful;
    /**
     * Type of transaction
     */
    private String transactionType;
    /**
     * Transaction state
     */
    private String transactionState;
    /**
     * Cart summary
     */
    private Basket basket;
    /**
     * Secure 3D field
     */
    private Secure3D secure3D;
    /**
     * Append CustomFields to the list.
     */
    private CustomFields[] customFields;
    /**
     * Key-value pair fields returned from a transaction lookup.
     */
    private LookupData lookupData;
    /**
     * Payment methods used by customer to fund payment
     */
    private PaymentMethod paymentMethodsUsed;
    /**
     * Debit order recurring payment details
     */
    private RecurringDetails recurringDetails;
    /**
     * EFT funding instrument details.
     */
    private EFTBase redirect;
    /**
     * Fraud management details.
     */
    private FmDetails fraud;
}
