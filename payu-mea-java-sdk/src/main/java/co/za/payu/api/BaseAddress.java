/**
 * PayU MEA JAVA SDK
 *
 * @copyright  Copyright (c) 2016 PayU
 * @license    http://opensource.org/licenses/LGPL-3.0  Open Software License (LGPL 3.0)
 * @link http://www.payu.co.za
 * @link http://help.payu.co.za/developers
 * @author Kenneth Onah <kenneth@netcraft-devops.com>
 */
package co.za.payu.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import co.za.payu.base.soap.PayUModel;

/**
 * Class BaseAddress
 *
 * Base Address object used as billing
 * address in a payment or extended for Shipping Address.
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseAddress extends PayUModel {

    /**
     * Line 1 of the Address (eg. number, street, etc).
     */
    private String line1;

    /**
     * Optional line 2 of the Address (eg. suite, apt #, etc.).
     */
    private String line2;

    /**
     * City name.
     */
    private String city;

    /**
     * 2 letter country code.
     */
    private String countryCode;

    /**
     * Zip code or equivalent is usually required for countries that have them. For list of countries that do not have postal codes please refer to http://en.wikipedia.org/wiki/Postal_code.
     */
    private String postalCode;

    /**
     * 2 letter code for US states, and the equivalent for other countries.
     */
    private String state;

    /**
     * Default Constructor
     */
    public BaseAddress() {
    }

    /**
     * Parameterized Constructor
     */
    public BaseAddress(String line1, String countryCode) {
        this.line1 = line1;
        this.countryCode = countryCode;
    }
}
