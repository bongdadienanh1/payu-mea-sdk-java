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
 * Class Item
 *
 * Item details.
 *
 * @package co.za.payu.api
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Item extends PayUModel {
    /**
     * Stock keeping unit corresponding (SKU) to item.
     */
    private String sku;
    /**
     * Item name. 127 characters max.
     */
    private String name;
    /**
     * Description of the item.
     */
    private String description;
    /**
     * Number of a particular item. 10 characters max.
     */
    private String quantity;
    /**
     * Item cost. 10 characters max.
     */
    private String price;
    /**
     * 3-letter [currency code]
     */
    private String currency;
    /**
     * Tax of the item.
     */
    private String tax;
}
