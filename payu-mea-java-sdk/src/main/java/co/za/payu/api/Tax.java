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
 * Class Tax
 *
 * Tax information.
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Tax extends PayUModel {
    /**
     * The resource ID.
     */
    private String id;
    /**
     * The tax name. Maximum length is 20 characters.
     */
    private String name;
    /**
     * The rate of the specified tax. Valid range is from 0.001 to 99.999.
     */
    private float percent;
    /**
     * The tax as a monetary amount.
     */
    private Currency amount;
}
