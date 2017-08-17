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
 * Class RedirectUrls
 *
 * Set of redirect URLs.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RedirectUrls extends PayUModel {
    /**
     * Url where the customer would be redirected to after approving the payment
     * Required for Redirect Payment Page
     */
    private String returnUrl;
    /**
     * Url where the customer would be redirected to after canceling the payment.
     * Required for Redirect Payment Page
     */
    private String cancelUrl;
    /**
     * Url where the Instant Payment Notification requests are sent.
     */
    private String notifyUrl;
}
