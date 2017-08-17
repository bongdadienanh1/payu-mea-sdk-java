package co.za.payu.base;

public class Constants {
    private Constants() {}

    // General Constants
    // UTF-8 Encoding format
    public static final String ENCODING_FORMAT = "UTF-8";

    // Empty String
    public static final String EMPTY_STRING = "";

    // Account prefix used in config properties file
    public static final String ACCOUNT_PREFIX = "acct";

    // SOAP Payload format
    public static final String PAYLOAD_FORMAT_SOAP = "SOAP";

    // Default SDK configuration file name
    public static final String DEFAULT_CONFIGURATION_FILE = "sdk_config.properties";

    // HTTP Header Constants
    // HTTP Content-Type Header
    public static final String HTTP_CONTENT_TYPE_HEADER = "Content-Type";

    // HTTP Accept Header
    public static final String HTTP_ACCEPT_HEADER = "Accept";

    // User Agent Header
    public static final String USER_AGENT_HEADER = "User-Agent";

    // PayU Request ID Header
    public static final String PAYU_REQUEST_ID_HEADER = "PayU-Request-Id";

    // Constants key defined for configuration options in application properties
    // End point
    public static final String ENDPOINT = "service.EndPoint";

    // Authorization Header
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Service Redirect Endpoint
    public static final String SERVICE_REDIRECT_ENDPOINT = "service.RedirectURL";

    // Service DevCentral Endpoint
    public static final String SERVICE_DEVCENTRAL_ENDPOINT = "service.DevCentralURL";

    // Use Google App Engine
    public static final String GOOGLE_APP_ENGINE = "http.GoogleAppEngine";

    // Use HTTP Proxy
    public static final String USE_HTTP_PROXY = "http.UseProxy";

    // HTTP Proxy host
    public static final String HTTP_PROXY_HOST = "http.ProxyHost";

    // HTTP Proxy port
    public static final String HTTP_PROXY_PORT = "http.ProxyPort";

    // HTTP Proxy username
    public static final String HTTP_PROXY_USERNAME = "http.ProxyUserName";

    // HTTP Proxy password
    public static final String HTTP_PROXY_PASSWORD = "http.ProxyPassword";

    // HTTP Connection Timeout
    public static final String HTTP_CONNECTION_TIMEOUT = "http.ConnectionTimeOut";

    // HTTP Connection Retry
    public static final String HTTP_CONNECTION_RETRY = "http.Retry";

    // HTTP Read timeout
    public static final String HTTP_CONNECTION_READ_TIMEOUT = "http.ReadTimeOut";

    // HTTP Max Connections
    public static final String HTTP_CONNECTION_MAX_CONNECTION = "http.MaxConnection";

    // HTTP Configurations Defaults
    // HTTP Method Default
    public static final String HTTP_CONFIG_DEFAULT_HTTP_METHOD = "POST";

    // HTTP Device IP Address Key
    public static final String DEVICE_IP_ADDRESS = "http.IPAddress";

    // Credential Username suffix
    public static final String CREDENTIAL_USERNAME_SUFFIX = ".UserName";

    // Credential Password suffix
    public static final String CREDENTIAL_PASSWORD_SUFFIX = ".Password";

    // Credential Application ID
    public static final String CREDENTIAL_APPLICATIONID_SUFFIX = ".AppId";

    // Credential Subject
    public static final String CREDENTIAL_SUBJECT_SUFFIX = ".Subject";

    // Credential Signature
    public static final String CREDENTIAL_SIGNATURE_SUFFIX = ".Signature";

    // Credential Certificate Path
    public static final String CREDENTIAL_CERTPATH_SUFFIX = ".CertPath";

    // Credential Certificate Key
    public static final String CREDENTIAL_CERTKEY_SUFFIX = ".CertKey";

    // IPN endpoint property name
    public static final String IPN_ENDPOINT = "service.IPNEndpoint";

    // Platform Sandbox Endpoint
    public static final String WSDL_SANDBOX_ENDPOINT = "https://staging.payu.co.za/service/PayUAPI?wsdl";

    // Platform Live Endpoint
    public static final String WSDL_LIVE_ENDPOINT = "https://secure.payu.co.za/service/PayUAPI?wsdl";

    // SOAP Sandbox Endpoint
    public static final String SOAP_SANDBOX_ENDPOINT = "https://staging.payu.co.za/service/rpp.do";

    // SOAP Live Endpoint
    public static final String SOAP_LIVE_ENDPOINT = "https://secure.payu.co.za/service/rpp.do";

    // Approval url
    public static final String APPROVAL_URL = "https://%s.payu.co.za/rpp.do?PayUReference=%s";

    // Mode(sandbox/live)
    public static final String MODE = "mode";

    // SANDBOX Mode
    public static final String SANDBOX = "sandbox";

    // LIVE Mode
    public static final String LIVE = "live";

    // Web service username
    public static final String API_USERNAME = "clientId";

    // Web service password
    public static final String API_PASSWORD = "clientSecret";

    // Web service safekey
    public static final String API_SAFEKEY = "clientSecret";

    // SSLUtil JRE
    public static final String SSLUTIL_JRE = "sslutil.jre";

    // SSLUtil Protocol
    public static final String SSLUTIL_PROTOCOL = "sslutil.protocol";

    // Trust Certificate Location to be used to validate webhook certificates
    public static final String PAYU_TRUST_CERT_URL = "webhook.trustCert";

    // Default Trust Certificate that comes packaged with SDK.
    public static final String PAYU_TRUST_DEFAULT_CERT = "DigiCertSHA2ExtendedValidationServerCA.crt";

    // Webhook Id to be set for validation purposes
    public static final String PAYU_WEBHOOK_CERTIFICATE_AUTHTYPE = "webhook.authType";
}
