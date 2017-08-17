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

import co.za.payu.base.soap.PayUResource;

import java.util.Map;

/**
 * Class Payment
 *
 * Lets you create, process and manage redirect payments.
 *
 * @package co.za.payu.api
 */
public class Redirect extends PayUResource {
    public static final String REDIRECT_URL = "https://%s.payu.co.za/rpp.do?PayUReference=%s";

    /**
     * PayU redirect url. Customer is redirected to PayU to capture payment details.
     *
     * @return string|null
     */
    public String getPayURedirectUrl()
    {
        Map<String, String> config = getConfigurations();
        String mode = config.get("mode");

        String reference = getResponse().getPayUReference();

        if (mode.equals("") || reference.equals(""))
            return null;

        String url = String.format(Redirect.REDIRECT_URL, mode.equalsIgnoreCase("sandbox") ? "staging" : "secure", reference);

        return url;
    }
}
