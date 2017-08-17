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

/**
 * Class Payment
 *
 * Lets you create, process and manage payments.
 *
 * @package co.za.payu.api
 */
public class Payment extends PayUResource {

    public EFTBase getEftProUrl() {
        EFTBase redirect = getResponse().getRedirect();

        return redirect;
    }
}
