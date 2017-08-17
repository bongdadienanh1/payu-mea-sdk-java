package co.za.payu.base.soap;

import co.za.payu.api.Response;
import co.za.payu.base.*;
import co.za.payu.base.exception.BaseException;
import co.za.payu.base.exception.ActionRequiredException;
import co.za.payu.base.exception.HttpErrorException;
import co.za.payu.base.sdk.info.SDKVersionImpl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
     * Response after execution of API call
     */
    protected Response response;
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
     * @return	BasicAuthCredential instance with username, password and safekey stored in configuration file.
     */
    public static BasicAuthCredential initConfig(File file) throws PayUSOAPException {
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
     * @return	BasicAuthCredential instance with username, password and safekey in given properties.
     */
    public static BasicAuthCredential initConfig(Properties properties) {
        configurationMap = SDKUtil.constructMap(properties);
        configInitialized = true;
        return getBasicAuthCredential();
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
     * @return	BasicAuthCredential instance with username, password and safekey stored in given inputStream.
     */
    public static BasicAuthCredential initConfig(InputStream inputStream)
            throws PayUSOAPException {
        try {
            Properties properties = new Properties();
            properties.load(inputStream);

			/*
			 * Create a Map instance and combine it with default values
			 */
            configurationMap = SDKUtil.constructMap(properties);
            configInitialized = true;
            return getBasicAuthCredential();
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
     * Returns BasicAuthCredential instance using username, password and safekey loaded from configuration.
     * @return BasicAuthCredential instance.
     */
    public static BasicAuthCredential getBasicAuthCredential() {
        if(configInitialized){
            return new BasicAuthCredential(getUsername(), getPassword(), getSafekey(), configurationMap);
        }else{
            return new BasicAuthCredential(getUsername(), getPassword(), getSafekey());
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
     * Configures and executes REST call
     *
     * @param <T> Response Type for de-serialization
     * @param apiContext {@link APIContext} to be used for the call.
     * @param resourcePath Resource URI path
     * @param payLoad Payload to Service
     * @param clazz {@link Class} object used in De-serialization
     * @return T
     * @throws PayUSOAPException
     */
    public static <T> T configureAndExecute(APIContext apiContext,
                                            String resourcePath, String payLoad,
                                            Class<T> clazz) throws PayUSOAPException {
        return configureAndExecute(apiContext, resourcePath, payLoad, clazz, null);
    }

    /**
     * Configures and executes REST call: Supports JSON
     *
     * @param <T> Response Type for de-serialization
     * @param apiContext {@link APIContext} to be used for the call.
     * @param resourcePath Resource URI path
     * @param payLoad Payload to Service
     * @param clazz {@link Class} object used in De-serialization
     * @param accessToken Access Token to be used instead of apiContext
     * @return T
     * @throws PayUSOAPException
     */
    public static <T> T configureAndExecute(APIContext apiContext,
                                            String resourcePath, String payLoad,
                                            Class<T> clazz, String accessToken) throws PayUSOAPException {
        T t = null;
        Map<String, String> cMap;
        String requestId;
        Map<String, String> headersMap;
        if (apiContext != null) {
            if (apiContext.getSdkVersion() != null) {
                apiContext.setSdkVersion(new SDKVersionImpl());
            }

            if (apiContext.getConfigurationMap() != null) {
                cMap = SDKUtil.combineDefaultMap(apiContext
                        .getConfigurationMap());
            } else {
                if (!configInitialized) {
                    initializeToDefault();
                }

				/*
				 * The Map returned here is already combined with default values
				 */
                cMap = new HashMap<String, String>(
                        configurationMap);
            }
            headersMap = apiContext.getHTTPHeaders();
            requestId = apiContext.getRequestId();

            APICallPreHandler apiCallPreHandler = createAPICallPreHandler(cMap,
                    payLoad, resourcePath, headersMap, requestId,
                    apiContext.getSdkVersion());
            HttpConfiguration httpConfiguration = createHttpConfiguration(cMap, apiCallPreHandler);
            t = execute(apiCallPreHandler, httpConfiguration, clazz);
        }
        return t;
    }

    /**
     * Returns a implementation of {@link APICallPreHandler} for the underlying
     * layer.
     *
     * @param configurationMap configuration Map
     * @param payLoad Raw payload
     * @param resourcePath URI part of the resource operated on
     * @param headersMap Custom HTTP headers map
     * @param requestId PayU Request Id
     * @param sdkVersion {@link SDKVersion} instance
     * @return APICallPreHandler
     */
    public static APICallPreHandler createAPICallPreHandler(
            Map<String, String> configurationMap, String payLoad,
            String resourcePath, Map<String, String> headersMap,
            String requestId, SDKVersion sdkVersion) {
        APICallPreHandler apiCallPreHandler = null;
        SOAPAPICallPreHandler soapAPICallPreHandler = new SOAPAPICallPreHandler(
                configurationMap, headersMap);
        soapAPICallPreHandler.setResourcePath(resourcePath);
        soapAPICallPreHandler.setRequestId(requestId);
        soapAPICallPreHandler.setPayLoad(payLoad);
        soapAPICallPreHandler.setSdkVersion(sdkVersion);
        apiCallPreHandler = soapAPICallPreHandler;
        return apiCallPreHandler;
    }

    /**
     * Execute the API call and return response
     *
     * @param <T> Generic Type for response object construction
     * @param apiCallPreHandler Implementation of {@link APICallPreHandler}
     * @param httpConfiguration {@link HttpConfiguration}
     * @param clazz Response Object class
     * @return Response Type
     * @throws PayUSOAPException
     */
    private static <T> T execute(APICallPreHandler apiCallPreHandler,
                                 HttpConfiguration httpConfiguration, Class<T> clazz) throws PayUSOAPException {
        T t = null;
        ConnectionManager connectionManager;
        HttpConnection httpConnection;
        Map<String, String> headers;
        String responseString;
        try {

            // Headers
            headers = apiCallPreHandler.getHeaderMap();

            // HttpConnection Initialization
            connectionManager = ConnectionManager.getInstance();
            httpConnection = connectionManager.getConnection(httpConfiguration);
            httpConnection.createAndconfigureHttpConnection(httpConfiguration);

            // capture request and log if conditions are met
            LASTREQUEST.set(apiCallPreHandler.getPayLoad());
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
            responseString = httpConnection.execute(null, apiCallPreHandler.getPayLoad(), headers);

            // capture response and log if conditions are met
            LASTRESPONSE.set(responseString);
            if (!Constants.LIVE.equalsIgnoreCase(mode)) {
                log.debug("response: " + LASTRESPONSE.get());
            }
            if (clazz != null) {
                t = JSONFormatter.fromJSON(responseString, clazz);
            }
        } catch (ActionRequiredException e) {
            throw PayUSOAPException.createFromHttpErrorException(e);
        } catch (HttpErrorException e) {
            throw PayUSOAPException.createFromHttpErrorException(e);
        } catch (Exception e) {
            throw new PayUSOAPException(e.getMessage(), e);
        }

        return t;
    }

    /**
     * Utility method that creates a {@link HttpConfiguration} object from the
     * passed information
     *
     * @param configurationMap Configuration to base the construction upon.
     * @param apiCallPreHandler {@link APICallPreHandler} for retrieving EndPoint
     * @return
     * @throws BaseException
     * @throws PayUSOAPException
     */
    private static HttpConfiguration createHttpConfiguration(Map<String, String> configurationMap,
                                                             APICallPreHandler apiCallPreHandler) throws PayUSOAPException {
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        String endpoint = apiCallPreHandler.getEndPoint();
        if (endpoint == null || endpoint.isEmpty()) {
            throw new PayUSOAPException("The endpoint could not be fetched properly. You may be missing `mode` in your configuration.");
        }
        httpConfiguration.setEndPointUrl(apiCallPreHandler.getEndPoint());
        httpConfiguration
                .setGoogleAppEngine(Boolean.parseBoolean(configurationMap
                        .get(Constants.GOOGLE_APP_ENGINE)));
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
     * @deprecated Please use static method `getCredential` instead.
     *
     * Returns ClientCredentials with username, password and safekey from configuration Map.
     *
     * @return Client credentials
     */
    public ClientCredentials getClientCredential() {
        return PayUResource.getCredential();
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
}
