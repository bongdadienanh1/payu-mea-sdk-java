package co.za.payu.base.soap;

import co.za.payu.api.IRequest;
import co.za.payu.api.IResponse;
import co.za.payu.base.*;
import co.za.payu.base.exception.*;
import co.za.payu.base.sdk.info.SDKVersionImpl;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * PayUResource acts as a base class for SOAP enabled resources.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayUResource extends PayUModel {

    private static final Logger log = LoggerFactory.getLogger(PayUResource.class);

    /**
     * APIContext instance
     */
    protected APIContext apiContext;

	/**
	 * The class relies on an implementation of APICallPreHandler (here
	 * SOAPAPICallPreHandler) to get access to endpoint, HTTP headers, and
	 * payload.
	 */
    /**
     * Map used in dynamic configuration
     */
    private static Map<String, String> configurationMap;

    /**
     * Configuration enabled flag
     */
    private static boolean configInitialized = false;

    /**
     * DoTransaction request payload
     */
    private IRequest iRequest;

    /**
     * Last request sent to Service
     */
    private static final ThreadLocal<String> LASTREQUEST = new ThreadLocal<String>();

    /**
     * Last response returned form Service
     */
    private static final ThreadLocal<String> LASTRESPONSE = new ThreadLocal<String>();

    /**
     * Initialize the system using a File(Properties file). The system is
     * initialized using the given file and if the initialization succeeds the
     * default 'sdk_config.properties' can only be loaded by calling the method
     * initializeToDefault()
     *
     * @param file
     *            File object of a properties entity
     * @throws PayUSOAPException
     * @return	AuthCredential instance with username, password and safekey stored in configuration file.
     */
    public static AuthCredential initConfig(File file) throws PayUSOAPException {
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("File doesn't exist: "
                        + file.getAbsolutePath());
            }
            FileInputStream fis = new FileInputStream(file);
            return initConfig(fis);
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            throw new PayUSOAPException(ioe.getMessage(), ioe);
        }

    }

    /**
     * Initialize using Properties. The system is initialized using the given
     * properties object and if the initialization succeeds the default
     * 'sdk_config.properties' can only be loaded by calling the method
     * initializeToDefault()
     *
     * @param properties
     *            Properties object
     * @return	AuthCredential instance with username, password and safekey in given properties.
     */
    public static AuthCredential initConfig(Properties properties) {
        configurationMap = SDKUtil.constructMap(properties);
        configInitialized = true;
        return getAuthCredential();
    }

    /**
     * Initialize using {@link InputStream}(of a Properties file).. The system
     * is initialized using the given {@link InputStream} and if the
     * initialization succeeds the default 'sdk_config.properties' can only be
     * loaded by calling the method initializeToDefault(). The system is
     * initialized with the information after loading defaults for the
     * parameters that are not passed as part of the configuration. For defaults
     * see {@link ConfigManager}
     *
     * @param inputStream
     *            InputStream
     * @throws PayUSOAPException
     * @return	AuthCredential instance with username, password and safekey stored in given inputStream.
     */
    public static AuthCredential initConfig(InputStream inputStream)
            throws PayUSOAPException {
        try {
            Properties properties = new Properties();
            properties.load(inputStream);

			/*
			 * Create a Map instance and combine it with default values
			 */
            configurationMap = SDKUtil.constructMap(properties);
            configInitialized = true;
            return getAuthCredential();
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            throw new PayUSOAPException(ioe.getMessage(), ioe);
        }
    }

    /**
     * Return Username from configuration Map
     */
    public static String getUsername() {
        return configurationMap.get(Constants.API_USERNAME);
    }

    /**
     * Returns Password from configuration Map
     */
    public static String getPassword() {
        return configurationMap.get(Constants.API_PASSWORD);
    }

    /**
     * Returns Safekey from configuration Map
     */
    public static String getSafekey() {
        return configurationMap.get(Constants.API_SAFEKEY);
    }

    /**
     * Returns request payload for doTransaction SOAP call
     */
    public IRequest getRequest() {
        return this.iRequest;
    }

    /**
     * Sets request payload for doTransaction SOAP call
     */
    public void setRequest(IRequest iRequest) {
        this.iRequest = iRequest;
    }

    /**
     * Returns AuthCredential instance using username, password and safekey loaded from configuration.
     * @return AuthCredential instance.
     */
    public static AuthCredential getAuthCredential() {
        if(configInitialized){
            return new AuthCredential(getUsername(), getPassword(), getSafekey(), configurationMap);
        }else{
            return new AuthCredential(getUsername(), getPassword(), getSafekey());
        }
    }

    /**
     * Initialize to default properties
     *
     * @throws PayUSOAPException
     */
    public static void initializeToDefault() throws PayUSOAPException {
        configurationMap = SDKUtil.combineDefaultMap(ConfigManager
                .getInstance().getConfigurationMap());
    }

    /**
     * Returns the last request sent to the Service
     *
     * @return Last request sent to the server
     */
    public static String getLastRequest() {
        return LASTREQUEST.get();
    }

    /**
     * Returns the last response returned by the Service
     *
     * @return Last response got from the Service
     */
    public static String getLastResponse() {
        return LASTRESPONSE.get();
    }

    public static Map<String, String> getConfigurations() {
        return configurationMap;
    }

    /**
     * Configures and executes REST call: Supports JSON
     *
     * @param apiContext {@link APIContext} to be used for the call.
     * @param payload {@link PayUModel} to be used as request payload for the call.
     * @param soapAction SOAP action to call
     * @return IResponse
     * @throws PayUSOAPException
     */
    public static IResponse configureAndExecute(APIContext apiContext, IRequest payload, String soapAction) throws PayUSOAPException {
        String requestId;
        IResponse response = null;
        Map<String, String> cMap;
        Map<String, String> headersMap;

        if (apiContext != null) {
            if (apiContext.getSdkVersion() == null) {
                apiContext.setSdkVersion(new SDKVersionImpl());
            }

            if (apiContext.getConfigurationMap() != null) {
                cMap = SDKUtil.combineDefaultMap(apiContext.getConfigurationMap());
            } else {
                if (!configInitialized) {
                    initializeToDefault();
                }

				/*
				 * The Map returned here is already combined with default values
				 */
                cMap = new HashMap<String, String>(configurationMap);
            }
            headersMap = apiContext.getHTTPHeaders();
            requestId = apiContext.getRequestId();

            APICallPreHandler apiCallPreHandler = createAPICallPreHandler(cMap,
                    soapAction, headersMap, requestId, payload, apiContext.getAccountPrefix(), apiContext.getSdkVersion());

            ConnectionConfiguration httpConfiguration = createHttpConfiguration(cMap, apiCallPreHandler);

            response = execute(apiCallPreHandler, httpConfiguration);
        }

        return response;
    }

    /**
     * Returns a implementation of {@link APICallPreHandler} for the underlying
     * layer.
     *
     * @param configurationMap configuration Map
     * @param headersMap Custom HTTP headers map
     * @param requestId PayU Request Id
     * @param request request payload
     * @param sdkVersion {@link SDKVersion} instance
     * @return APICallPreHandler
     */
    public static APICallPreHandler createAPICallPreHandler(
            Map<String, String> configurationMap,
            String soapAction, Map<String, String> headersMap,
            String requestId, IRequest request, String accountPrefix,
            SDKVersion sdkVersion
    ) {
        APICallPreHandler apiCallPreHandler;
        SOAPAPICallPreHandler soapAPICallPreHandler = new SOAPAPICallPreHandler(configurationMap, headersMap);
        soapAPICallPreHandler.setAccountPrefix(accountPrefix)
                .setSoapAction(soapAction)
                .setRequestId(requestId)
                .setRequestPayload(request)
                .setSdkVersion(sdkVersion)
                .addAPIParameters();

        String paymentMethods = request.getSupportedPaymentMethods();
        if(paymentMethods == null || paymentMethods.isEmpty()) {
            soapAPICallPreHandler.addSupportedPaymentMethods();
        }

        apiCallPreHandler = soapAPICallPreHandler;

        return apiCallPreHandler;
    }

    /**
     * Execute the API call and return response
     *
     * @param apiCallPreHandler Implementation of {@link APICallPreHandler}
     * @param httpConfiguration {@link ConnectionConfiguration}
     * @return Response Type
     * @throws PayUSOAPException
     */
    private static IResponse execute(APICallPreHandler apiCallPreHandler,
                                           ConnectionConfiguration httpConfiguration) throws PayUSOAPException {
        Connection connection;
        String responseString;
        IResponse response;

        Map<String, String> headers;

        ConnectionManager connectionManager;

        try {

            // Headers
            headers = apiCallPreHandler.getHeaderMap();

            // HttpConnection Initialization
            connectionManager = ConnectionManager.getInstance();
            connection = connectionManager.getConnection();
            //connection.createAndconfigureConnection(httpConfiguration);

            // capture request and log if conditions are met
            LASTREQUEST.set(JSONFormatter.toJSON(apiCallPreHandler.getRequestPayload()));
            String mode = "";
            if (configurationMap != null) {
                mode = configurationMap.get(Constants.MODE);
            } else if (apiCallPreHandler.getConfigurationMap() != null) {
                mode = apiCallPreHandler.getConfigurationMap().get(Constants.MODE);
            }
            if (Constants.LIVE.equalsIgnoreCase(mode) && log.isDebugEnabled()) {
                log.warn("Log level cannot be set to DEBUG in " + Constants.LIVE + " mode. Skipping request/response logging...");
            }
            if (!Constants.LIVE.equalsIgnoreCase(mode)) {
                log.debug("request header: " + headers.toString());
                log.debug("request body: " + LASTREQUEST.get());
            }

            // send request and receive response
            response = connection.execute(apiCallPreHandler);

            responseString = JSONFormatter.toJSON(response);

            // capture response and log if conditions are met
            LASTRESPONSE.set(responseString);
            if (!Constants.LIVE.equalsIgnoreCase(mode)) {
                log.debug("response: " + LASTRESPONSE.get());
            }

        } catch (ActionRequiredException e) {
            throw PayUSOAPException.createFromHttpErrorException(e);
        } catch (HttpErrorException e) {
            throw PayUSOAPException.createFromHttpErrorException(e);
        } catch (Exception e) {
            throw new PayUSOAPException(e.getMessage(), e);
        }

        return response;
    }

    /**
     * Utility method that creates a {@link ConnectionConfiguration} object from the
     * passed information
     *
     * @param configurationMap Configuration to base the construction upon.
     * @param apiCallPreHandler {@link APICallPreHandler} for retrieving EndPoint
     * @return
     * @throws BaseException
     * @throws PayUSOAPException
     */
    private static ConnectionConfiguration createHttpConfiguration(Map<String, String> configurationMap,
                                                                   APICallPreHandler apiCallPreHandler) throws PayUSOAPException {
        ConnectionConfiguration httpConfiguration = new ConnectionConfiguration();
        String endpoint = apiCallPreHandler.getServiceEndPoint();
        if (endpoint == null || endpoint.isEmpty()) {
            throw new PayUSOAPException("The endpoint could not be fetched properly. You may be missing `mode` in your configuration.");
        }
        httpConfiguration.setEndPointUrl(apiCallPreHandler.getServiceEndPoint());
        if (Boolean.parseBoolean(configurationMap
                .get((Constants.USE_HTTP_PROXY)))) {
            httpConfiguration.setProxyPort(Integer.parseInt(configurationMap
                    .get((Constants.HTTP_PROXY_PORT))));
            httpConfiguration.setProxyHost(configurationMap
                    .get((Constants.HTTP_PROXY_HOST)));
            httpConfiguration.setProxyUserName(configurationMap
                    .get((Constants.HTTP_PROXY_USERNAME)));
            httpConfiguration.setProxyPassword(configurationMap
                    .get((Constants.HTTP_PROXY_PASSWORD)));
        }
        httpConfiguration.setConnectionTimeout(Integer
                .parseInt(configurationMap
                        .get(Constants.HTTP_CONNECTION_TIMEOUT)));
        httpConfiguration.setMaxRetry(Integer.parseInt(configurationMap
                .get(Constants.HTTP_CONNECTION_RETRY)));
        httpConfiguration.setReadTimeout(Integer.parseInt(configurationMap
                .get(Constants.HTTP_CONNECTION_READ_TIMEOUT)));
        httpConfiguration.setMaxHttpConnection(Integer
                .parseInt(configurationMap
                        .get(Constants.HTTP_CONNECTION_MAX_CONNECTION)));
        httpConfiguration.setIpAddress(configurationMap
                .get(Constants.DEVICE_IP_ADDRESS));
        return httpConfiguration;
    }

    /**
     * Returns ClientCredentials with username, password and safekey from configuration Map
     *
     * @return Client credentials
     */
    public static ClientCredentials getCredential() {
        ClientCredentials credentials = new ClientCredentials();
        Properties configFileProperties = getConfigFileProperties();
        addConfigurations(configFileProperties);
        credentials.setUsername(configurationMap.get(Constants.API_USERNAME));
        credentials.setPassword(configurationMap.get(Constants.API_PASSWORD));
        credentials.setSafekey(configurationMap.get(Constants.API_SAFEKEY));
        return credentials;
    }
    /**
     * Fetches the properties from default configuration file.
     *
     * @return {@link Properties}
     */
    private static Properties getConfigFileProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(
                    new File(PayUResource.class.getClassLoader().getResource(Constants.DEFAULT_CONFIGURATION_FILE).getFile())));
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return properties;
    }

    /**
     * Merges properties object with the configuration hash map. The configuration values are given higher priority.
     *
     * @param properties
     */
    private static void addConfigurations(Properties properties) {
        if (configurationMap == null) {
            configurationMap = new HashMap<String, String>();
        }
        if (properties != null) {
            for (final String name : properties.stringPropertyNames()) {
                if (!configurationMap.containsKey(name)) {
                    configurationMap.put(name, properties.getProperty(name));
                }
            }
        }
    }

    /**
     * Gets the details of a transaction. Merchant reference or PayU reference
     * must be provide but not both. Providing both references will cause the
     * request to fail.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse get(APIContext apiContext) throws PayUSOAPException {
        String soapAction = "getTransaction";
        this.apiContext = apiContext;

        IResponse response = configureAndExecute(apiContext, this.getRequest(), soapAction);

        apiContext.setRequestId(null);

        return response;
    }

    /**
     * Creates and processes a payment. In the JSON request body, include a `payment` object with the intent, customer,
     * and transactions. Also include a notification URL in the `payment` object.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse create(APIContext apiContext) throws PayUSOAPException {
        String soapAction = "doTransaction";
        this.apiContext = apiContext;

        IResponse response = configureAndExecute(apiContext, this.getRequest(), soapAction);

        apiContext.setRequestId(null);

        return response;
    }

    /**
     * Setups a redirect payment before redirecting to PayU. In the JSON request body, include a `payment` object with
     * the intent, customer, and transactions. Also include return and cancel URLs in the `payment` object.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse setup(APIContext apiContext) throws PayUSOAPException {
        String soapAction = "setTransaction";
        this.apiContext = apiContext;

        IResponse response = configureAndExecute(apiContext, this.getRequest(), soapAction);

        apiContext.setRequestId(null);

        return response;
    }

    /**
     * Creates and processes a finalized payment. In the JSON request body, include a `payment` object with the intent, customer,
     * and transactions. For PayU payments, include redirect URLs in the `payment` object.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse capture(APIContext apiContext) throws PayUSOAPException {

        return this.create(apiContext);
    }

    /**
     * Refunds a captured/finalized payment. The request must include a PayU reference and merchant reference.
     * For PayU payments, include redirect URLs in the `payment` object.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse refund(APIContext apiContext) throws PayUSOAPException {

        return this.create(apiContext);
    }

    /**
     * Voids a authorized/reserved payment. The request must include a PayU reference.
     * For PayU payments, include redirect URLs in the `payment` object.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse voidTransaction(APIContext apiContext) throws PayUSOAPException {

        return this.create(apiContext);
    }

    /**
     * The lookup transaction method is used to lookup various details regarding users, transactions and services.
     * The core parameters identify the merchant, the lookup type and additional custom fields.
     * @param apiContext {@link APIContext } used for the API call.
     * @return IResponse response object
     * @throws PayUSOAPException
     */
    public IResponse lookup(APIContext apiContext) throws PayUSOAPException {

        String soapAction = "getLookupTransaction";
        this.apiContext = apiContext;

        IResponse response = configureAndExecute(apiContext, this.getRequest(), soapAction);

        apiContext.setRequestId(null);

        return response;
    }
}
