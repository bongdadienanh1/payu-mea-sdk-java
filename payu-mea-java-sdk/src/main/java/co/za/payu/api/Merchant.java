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
 * Class Merchant
 *
 * A resource representing a Merchant who receives the funds and fulfills the order.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Merchant extends PayUModel {
    /**
     * Email address representing the Merchant. 127 characters max.
     */
    private String email;

    /**
     * External Remember Me id representing the Merchant
     */
    private String merchantId;

    /**
     * Account Number representing the Merchant
     */
    private String accountNumber;

    /**
     * First name of the Merchant.
     */
    private String firstName;

    /**
     * Last name of the Merchant.
     */
    private String lastName;

    /**
     * Phone number representing the Merchant. 20 characters max.
     */
    private Phone phone;

    /**
     * Default Constructor
     */
    public Merchant() {
    }
}
