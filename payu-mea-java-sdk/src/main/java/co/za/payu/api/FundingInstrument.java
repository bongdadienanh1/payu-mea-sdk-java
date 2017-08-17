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
 * Class FundingInstrument
 *
 * A resource representing a Customer's funding instrument.
 * An instance of this schema is valid if and only if it is valid against exactly one of these supported properties
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FundingInstrument extends PayUModel {
    /**
     * Save payment card details for future payment.
     */
    private boolean storeCard;
    /**
     * Credit Card instrument.
     */
    private CreditCard creditCard;
    /**
     * Payment Card information.
     */
    private PaymentCard paymentCard;
    /**
     * eBucks payment details.
     */
    private Ebucks ebucks;
    /**
     * EFT funding instrument details.
     */
    private EFTBase eft;
    /**
     * PayU vaulted credit Card instrument.
     */
    private CreditCardToken creditCardToken;

    public FundingInstrument(){
    }
}
