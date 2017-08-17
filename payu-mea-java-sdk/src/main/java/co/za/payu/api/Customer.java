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
 * Class Customer
 *
 * A resource representing a customer that funds a payment.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Customer extends PayUModel {

    /**
     * IP address of customer
     */
    private String ipAddress;
    /**
     * Payment method being used - PayPal Wallet payment, Bank Direct Debit  or Direct Credit card.
     */
    private String paymentMethod;

    /**
     * List of funding instruments to fund the payment. 'OneOf' funding_instruments,funding_option_id to be used to identify the specifics of payment method passed.
     */
    private List<FundingInstrument> fundingInstruments;

    /**
     * Information related to the Payer.
     */
    private CustomerInfo customerInfo;

    /**
     * Default Constructor
     */
    public Customer() {
    }
}
