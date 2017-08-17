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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

/**
 * Class Ebucks
 *
 * Lets you create, process and manage ebucks payments.
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Ebucks extends PayUResource {

    public static final String PAYMENT = "PAYMENT";
    public static final String VALIDATE_OTP = "VALIDATE_OTP";
    public static final String GENERATE_OTP = "GENERATE_OTP";
    public static final String RESET_PASSWORD = "RESET_PASSWORD";
    public static final String AUTHENTICATE_ACCOUNT = "AUTHENTICATE_ACCOUNT";

    /**
     * The Type of action being performed.
     * Valid types [AUTHENTICATE_ACCOUNT, GENERATE_OTP, RESET_PASSWORD, VALIDATE_OTP]
     */
     private String action;
    /**
     * Metadata for identifying the type of action performed.
     */
     private String authenticateAccountType;
    /**
     * eBucks member's card number/Identification.
     */
     private String ebucksMemberIdentifier;
    /**
     * Pin number for the eBucks member login.
     */
     private String ebucksPin;
    /**
     * Metadata for identifying the type of action performed.
     */
     private String generateOTPType;
    /**
     * Amounts in eBucks.
     */
     private String ebucksAmount;
    /**
     * Metadata for identifying the type of action performed.
     */
     private String resetPasswordType;
    /**
     * Metadata for identifying the type of action performed.
     */
     private String validateOTPType;
    /**
     * OTP provided by the customer.
     */
     private String ebucksOtp;
    /**
     * eBucks account number
     */
     private String ebucksAccountNumber;
    /**
     * eBucks destination account number.
     */
     private String ebucksDestination;
}
