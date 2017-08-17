package co.za.payu.api;

import co.za.payu.base.soap.PayUModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Class NameValuePair
 *
 * NameValuePair class contains data key-value pair
 *
 * @package co.za.payu.api
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class NameValuePair extends PayUModel {

    /**
     * Key for the name value pair.  The value name types should be correlated
     */
    private String name;

    /**
     * Value for the name value pair.
     */
    private String value;

    /**
     * Default Constructor
     */
    public NameValuePair() {
    }

    /**
     * Parameterized Constructor
     */
    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
