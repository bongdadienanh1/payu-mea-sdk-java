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
 * Class ShippingInfo
 *
 * Shipping information for the invoice recipient.
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ShippingInfo extends PayUModel {
    /**
     * The shipping id.
     */
    private String id;
    /**
     * The recipient first name. Maximum length is 30 characters.
     */
    private String firstName;
    /**
     * The recipient last name. Maximum length is 30 characters.
     */
    private String lastName;
    /**
     * The recipient email.
     */
    private String email;
    /**
     * The recipient company business name. Maximum length is 100 characters.
     */
    private String businessName;
    /**
     * Phone number of recipient
     */
    private String phone;
    /**
     * Shipping method
     *
     * for valid codes
     * @see "https://help.payu.co.za/display/developers/Fraud+Prevention+Service"
     */
    private String method;
    /**
     * Shipping address of the recipient.
     */
    private ShippingAddress shippingAddress;
    /**
     * Shipping cost associated with the shipping option chosen by the customer.
     */
    private ShippingCost shippingCost;
}
