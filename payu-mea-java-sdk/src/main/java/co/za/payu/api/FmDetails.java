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
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;
/**
 * Class FmDetails
 *
 * Details of Fraud Management (FM).
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FmDetails extends PayUModel {
    /**
     * Check Fraud Override filter.
     */
    private String checkFraudOverride;
    /**
     * Merchant website
     */
    private String merchantWebsite;
    /**
     * Finger print of client machine.
     */
    private String pcFingerPrint;
    /**
     * Fraud management processing result code.
     */
    private String resultCode;
    /**
     * Fraud management processing result message.
     */
    private String resultMessage;
}
