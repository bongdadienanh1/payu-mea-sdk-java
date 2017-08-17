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

import java.util.List;

/**
 * Class Response
 *
 * Request class accepts a request resource and returns a re-formatted request payload
 *
 * @package co.za.payu.api
 */
public class Request extends PayUModel {
    /**
     * @var array request parameters
     */
    private List payload;
}
