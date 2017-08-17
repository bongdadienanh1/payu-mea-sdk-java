package co.za.payu.base;

import co.za.payu.base.util.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.*;

/**
 * <code>ConfigManager</code> loads configuration from 'sdk_config.properties'
 * file found in the classpath. There are certain default parameters that the
 * system chooses to use if not seen in the configuration. They are
 * enumerated below with the defaults is parenthesis
 *
 * http.ConnectionTimeOut(5000 ms), http.Retry(2), http.ReadTimeOut(30000 ms),
 * http.MaxConnections(100), http.IPAddress(127.0.0.1),
 * http.GoogleAppEngine(false)
 *
 */
public final class ConfigManager {
    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);

    /**
     * Singleton instance variable
     */
    private static ConfigManager conf;

    /**
     * Underlying property implementation
     */
    private Properties properties;

    /**
     * Initialized notifier
     */
    private boolean propertyLoaded = false;

    /**
     * Map View of internal {@link Properties}
     */
    private Map<String, String> mapView = null;

    /**
     * Map View of internal Default {@link Properties}
     */
    private static Map<String, String> defaultMapView = null;

    /**
     * Default {@link Properties}
     */
    private static final Properties DEFAULT_PROPERTIES;

    // Initialize DEFAULT_PROPERTIES
    static {
        DEFAULT_PROPERTIES = new Properties();
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_TIMEOUT, "5000");
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_RETRY, "2");
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_READ_TIMEOUT, "30000");
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_MAX_CONNECTION, "100");
        DEFAULT_PROPERTIES.put(Constants.DEVICE_IP_ADDRESS, "127.0.0.1");
        DEFAULT_PROPERTIES.put(Constants.GOOGLE_APP_ENGINE, "false");
        DEFAULT_PROPERTIES.put(Constants.SSLUTIL_JRE, "SunJSSE");
        DEFAULT_PROPERTIES.put(Constants.SSLUTIL_PROTOCOL, "TLS");
        DEFAULT_PROPERTIES.put(Constants.PAYU_TRUST_CERT_URL, "DigiCertSHA2ExtendedValidationServerCA.crt");
        DEFAULT_PROPERTIES.put(Constants.PAYU_WEBHOOK_CERTIFICATE_AUTHTYPE, "RSA");
        defaultMapView = new HashMap<String, String>();
        for (Object object : DEFAULT_PROPERTIES.keySet()) {
            defaultMapView.put(object.toString().trim(), DEFAULT_PROPERTIES
                    .getProperty(object.toString()).trim());
        }
    }

    /**
     * Private constructor
     */
    private ConfigManager() {

		/*
		 * Load configuration for default 'sdk_config.properties'
		 */
        ResourceLoader resourceLoader = new ResourceLoader(
                Constants.DEFAULT_CONFIGURATION_FILE);
        properties = new Properties();
        try {
            InputStream inputStream = resourceLoader.getInputStream();
            properties.load(inputStream);
        } catch (IOException e) {
            // We tried reading the config, but it seems like you dont have it. Skipping...
            log.debug(Constants.DEFAULT_CONFIGURATION_FILE + " not present. Skipping...");
        } catch (AccessControlException e) {
            log.debug("Unable to read " + Constants.DEFAULT_CONFIGURATION_FILE + ". Skipping...");
        } finally {
            setPropertyLoaded(true);
        }
    }

    /**
     * Singleton accessor method
     *
     * @return ConfigManager object
     */
    public static ConfigManager getInstance() {
        synchronized (ConfigManager.class) {
            if (conf == null) {
                conf = new ConfigManager();
            }
        }
        return conf;
    }

    /**
     * Returns the Default {@link Properties} of System Configuration
     *
     * @return Default {@link Properties}
     */
    public static Properties getDefaultProperties() {
        return DEFAULT_PROPERTIES;
    }

    /**
     * Returns a {@link Map} view of Default {@link Properties}
     *
     * @return {@link Map} view of Default {@link Properties}
     */
    public static Map<String, String> getDefaultSDKMap() {
        return new HashMap<String, String>(defaultMapView);
    }

    /**
     * Combines some {@link Properties} with Default {@link Properties}
     *
     * @param receivedProperties
     *            Properties used to combine with Default {@link Properties}
     *
     * @return Combined {@link Properties}
     */
    public static Properties combineDefaultProperties(
            Properties receivedProperties) {
        Properties combinedProperties = new Properties(getDefaultProperties());
        if ((receivedProperties != null) && (receivedProperties.size() > 0)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            try {
                receivedProperties.store(bos, null);
                combinedProperties.load(new ByteArrayInputStream(bos
                        .toByteArray()));
            } catch (IOException e) {
                // Something failed trying to load the properties. Skipping...
            }
        }
        return combinedProperties;
    }

    /**
     * Loads the internal properties with the passed {@link InputStream}
     *
     * @deprecated This code was used for older integrations. Not valid anymore. To be removed in the next major release.
     * @param is
     *            InputStream
     *
     * @throws IOException
     */
    public void load(InputStream is) throws IOException {
        properties = new Properties();
        properties.load(is);
        if (!propertyLoaded) {
            setPropertyLoaded(true);
        }
    }

    /**
     * Initializes the internal properties with the passed {@link Properties}
     * instance
     *
     * @deprecated This code was used for older integrations. Not valid anymore. To be removed in the next major release.
     * @param properties
     *            Properties instance
     *
     */
    public void load(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException(
                    "Initialization properties cannot be null");
        }
        this.properties = properties;
        if (!propertyLoaded) {
            setPropertyLoaded(true);
        }
    }

    /**
     * Constructs a {@link Map} object from the underlying {@link Properties}.
     * The {@link Properties} object is loaded for 'sdk_config.properties' file
     * in the classpath
     *
     * @return {@link Map}
     */
    public Map<String, String> getConfigurationMap() {
        if (mapView == null) {
            synchronized (DEFAULT_PROPERTIES) {
                mapView = new HashMap<String, String>();
                if (properties != null) {
                    for (Object object : properties.keySet()) {
                        mapView.put(object.toString().trim(), properties
                                .getProperty(object.toString()).trim());
                    }
                }
            }
        }
        return new HashMap<String, String>(mapView);
    }

    private void setPropertyLoaded(boolean propertyLoaded) {
        this.propertyLoaded = propertyLoaded;
    }
}