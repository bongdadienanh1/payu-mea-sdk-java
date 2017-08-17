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

import java.util.List;

/**
 * Class RecurringDetails
 *
 * The details of Debit Order payment setup on the customer account
 *
 * A transaction defines the contract of a payment - what is the payment for and who is fulfilling it.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RecurringDetails extends PayUModel {
    /**
     * Number of recurrences
     */
    private String recurrences;
    /**
     * Debit order statement description
     */
    private String statementDescription;
    /**
     * Debit order account management
     * Valid values: [PAYU, MERCHANT]
     */
    private String managedBy;
    /**
     * Debit order start date. Cannot be a date in the past.
     */
    private String startDate;
    /**
     * Debit order account is anonymous or otherwise
     */
    private String anonymousUser;
    /**
     * Debit order account is anonymous or otherwise
     */
    private String frequency;
    /**
     * Day on which Debit Order should be processed.
     * Valid values: [WEEKLY, LAST_DAY_OF_MONTH]
     */
    private String deductionDay;
    /**
     * Call center representative Id. Must be set to one of the IDs specified in the merchant config
     * `callcenter.allowed.reps` list. If there are no IDs in the `callcenter.allowed.reps` list the
     * callCenterRepId can be an empty string.
     */
    private List callCenterRepId;
    /**
     * Token representing the debit order setup.
     */
    private String recurringPaymentToken;
}
