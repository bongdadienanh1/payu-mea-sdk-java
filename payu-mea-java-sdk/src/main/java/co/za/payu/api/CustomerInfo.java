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
 * Class CustomerInfo
 *
 * A resource representing a information about Customer.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CustomerInfo extends PayUModel {
    /**
     * Email address representing the customer. 127 characters max.
     */
    private String email;

    /**
     * Account Number representing the Customer
     */
    private String accountNumber;

    /**
     * First name of the payer.
     */
    private String firstName;

    /**
     * Last name of the payer.
     */
    private String lastName;

    /**
     * PayU assigned ID.
     */
    private String customerId;

    /**
     * Phone number representing the payer. 20 characters max.
     */
    private String phone;

    /**
     * Registered phone country code of the customer.
     */
    private String countryCode;

    /**
     * Two-letter registered country code of the customer.
     */
    private String countryOfResidence;

    /**
     * Billing address of the Customer.
     */
    private Address billingAddress;

    /**
     * Default Constructor
     */
    public CustomerInfo() {
    }
}