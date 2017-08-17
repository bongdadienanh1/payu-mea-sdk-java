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
 * Class CartBase
 *
 * Base properties of a cart resource
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CartBase extends PayUModel {
    /**
     * Merchant identifier of the purchase unit. Optional parameter
     */
    private String referenceId;
    /**
     * Amount being collected.
     */
    private Amount amount;
    /**
     * Recipient of the funds in this transaction.
     */
    private Merchant merchant;
    /**
     * Description of what is being paid for.
     */
    private String description;
    /**
     * invoice number to track this payment
     */
    private String invoiceNumber;
    /**
     * List of items being paid for.
     */
    private ItemList itemList;
}
