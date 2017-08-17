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
 * Class Phone
 *
 * Information related to the Merchant.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Phone extends PayUModel {

    /**
     * Country code (from in E.164 format)
     */
    private String countryCode;

    /**
     * In-country phone number (from in E.164 format)
     */
    private String nationalNumber;

    /**
     * Phone extension
     */
    private String extension;

    /**
     * Default Constructor
     */
    public Phone() {
    }

    /**
     * Parameterized Constructor
     */
    public Phone(String countryCode, String nationalNumber) {
        this.countryCode = countryCode;
        this.nationalNumber = nationalNumber;
    }
}
