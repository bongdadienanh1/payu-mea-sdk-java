package co.za.payu.base;

import co.za.payu.base.exception.ActionRequiredException;
import co.za.payu.base.exception.AuthorizationException;

import java.util.Map;

/**
 * <code>APICallPreHandler</code> defines a high level abstraction for call
 * specific operations. PayPal REST API is provided by {@link co.za.payu.base.soap.SOAPAPICallPreHandler}
 */
public interface APICallPreHandler {

    /**
     * Returns headers for HTTP call
     *
     * @return Map of headers with name and value
     * @throws AuthorizationException
     */
    Map<String, String> getHeaderMap() throws AuthorizationException;

    /**
     * Returns the payload for the API call. The implementation should take care
     * in formatting the payload appropriately
     *
     * @return Payload as String
     */
    String getPayLoad();

    /**
     * Returns the endpoint for the API call. The implementation may calculate
     * the endpoint depending on parameters set on it. If no endpoint is found
     * in the passed configuration, then SANDBOX endpoints (hardcoded in
     * {@link Constants})are taken to be default for the API call.
     *
     * @return Endpoint String.
     */
    String getEndPoint();

    /**
     * Validates settings and integrity before call
     *
     * @throws ActionRequiredException
     */
    void validate() throws ActionRequiredException;

    /**
     * Return configurationMap
     *
     * @return configurationMap in this call pre-handler
     */
    public Map<String, String> getConfigurationMap();
}

