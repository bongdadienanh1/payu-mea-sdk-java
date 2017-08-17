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

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * Class Address
 *
 * Address object used as customer address in a payment or extended for Shipping Address.
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Address extends BaseAddress {

    /**
     * Phone number in E.123 format. 50 characters max.
     */
    private String phone;
}
