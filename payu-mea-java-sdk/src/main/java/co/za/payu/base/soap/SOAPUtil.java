package co.za.payu.base.soap;

import co.za.payu.base.Constants;
import co.za.payu.base.exception.PayUSOAPException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <code>SOAPUtil</code> acts as utility class used by REST API system
 */
public final class SOAPUtil {
    private SOAPUtil() {
    }

    /**
     * Formats the URI path for REST calls. Replaces any occurrences of the form
     * {name} in pattern with the corresponding value of key name in the passes
     * {@link Map}
     *
     * @param pattern URI pattern with named place holders
     * @param pathParameters Parameter {@link Map}
     * @return Processed URI path
     * @throws PayUSOAPException
     */
    public static String formatURIPath(String pattern,
                                       Map<String, String> pathParameters) throws PayUSOAPException {
        return formatURIPath(pattern, pathParameters, new HashMap<String, String>());
    }

    /**
     * Formats the URI path for REST calls. Replaces any occurrences of the form
     * {name} in pattern with the corresponding value of key name in the passes
     * {@link Map}. Query parameters are appended to the end of the URI path
     *
     * @param pattern URI pattern with named place holders
     * @param queryParameters Query parameters {@link Map}
     * @param pathParameters Parameter {@link String...}
     * @return Processed URI path
     * @throws PayUSOAPException
     */
    public static String formatURIPath(String pattern, Map<String, String> queryParameters, String... pathParameters)
            throws PayUSOAPException {
        Map<String, String> pathParams = new HashMap<String, String>();
        if (pathParameters != null) {
            for (int i = 0; i < pathParameters.length; i++) {
                pathParams.put(String.valueOf(i), pathParameters[i]);
            }
        }
        return formatURIPath(pattern, pathParams, queryParameters);
    }

    /**
     * Formats the URI path for REST calls. Replaces any occurrences of the form
     * {name} in pattern with the corresponding value of key name in the passes
     * {@link Map}. Query parameters are appended to the end of the URI path
     *
     * @param pattern URI pattern with named place holders
     * @param pathParameters Parameter {@link Map}
     * @param queryParameters Query parameters {@link Map}
     * @return Processed URI path
     * @throws PayUSOAPException
     */
    public static String formatURIPath(String pattern,
                                       Map<String, String> pathParameters,
                                       Map<String, String> queryParameters) throws PayUSOAPException {
        String formattedURIPath = null;
        if (pattern != null && pattern.trim().length() > 0
                && pathParameters != null && pathParameters.size() > 0) {
            for (Entry<String, String> entry : pathParameters.entrySet()) {
                String placeHolderName = "{" + entry.getKey().trim() + "}";
                if (pattern.contains(placeHolderName)) {
                    pattern = pattern.replace(placeHolderName, entry.getValue()
                            .trim());
                }
            }

        }
        formattedURIPath = pattern;
        if (queryParameters != null && queryParameters.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder(formattedURIPath);
            if (stringBuilder.toString().contains("?")) {
                if (!(stringBuilder.toString().endsWith("?")
                        || stringBuilder.toString().endsWith("&"))) {
                    stringBuilder.append("&");
                }
            } else {
                stringBuilder.append("?");
            }
            for (Entry<String, String> entry : queryParameters.entrySet()) {
                try {
                    stringBuilder
                            .append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                            .append("&");
                } catch (UnsupportedEncodingException e) {
                    // TODO
                }
            }
            formattedURIPath = stringBuilder.toString();
        }
        if (formattedURIPath.contains("{") || formattedURIPath.contains("}")) {
            throw new PayUSOAPException("Unable to formatURI Path : "
                    + formattedURIPath
                    + ", unable to replace placeholders with the map : "
                    + pathParameters);
        }
        return formattedURIPath;
    }

    /**
     * Remove null parameters from query string
     *
     * @param fString Formatted String
     * @return Nulls removed query string
     */
    private static String removeNullsInQS(String fString) {
        String formattedString = fString;
        if (fString != null && fString.length() != 0) {
            String[] parts = fString.split("\\?");

            // Process the query string part
            if (parts.length == 2) {
                String queryString = parts[1];
                String[] querys = queryString.split("&");
                if (querys.length > 0) {
                    StringBuilder strBuilder = new StringBuilder();
                    for (String query : querys) {
                        String[] valueSplit = query.split("=");
                        if (valueSplit.length == 2) {
                            if ("null".equalsIgnoreCase(valueSplit[1].trim())) {
                                continue;
                            } else if ("".equals(valueSplit[1].trim())) {
                                continue;
                            } else {
                                strBuilder.append(query).append("&");
                            }
                        } else if (valueSplit.length < 2) {
                            continue;
                        }
                    }
                    formattedString = (!strBuilder.toString().endsWith("&")) ? strBuilder
                            .toString() : strBuilder.toString().substring(0,
                            strBuilder.toString().length() - 1);
                }

                // append the query string delimiter
                formattedString = (parts[0].trim() + "?") + formattedString;
            }
        }
        return formattedString;
    }

    /**
     * Split the URI and form a Object array using the query string and values
     * in the provided map. The return object array is populated only if the map
     * contains valid value for the query name. The object array contains null
     * values if there is no value found in the map
     *
     * @param pattern URI pattern
     * @param containerMap Map containing the query name and value
     * @return Object array
     */
    private static Object[] splitParameters(String pattern,
                                            Map<?, ?> containerMap) {
        List<Object> objectList = new ArrayList<Object>();
        String[] query = pattern.split("\\?");
        if (query != null && query.length == 2 && query[1].contains("={")) {
            String[] queries = query[1].split("&");
            if (queries != null) {
                for (String q : queries) {
                    String[] params = q.split("=");
                    if (params != null && params.length == 2) {
                        String key = params[0].trim();
                        if (containerMap.containsKey(key)) {
                            Object object = containerMap.get(key);
                            try {
                                objectList.add(URLEncoder.encode(
                                        (String) object, Constants.ENCODING_FORMAT));
                            } catch (UnsupportedEncodingException e) {
                                // Ignore
                            }
                        } else {
                            objectList.add(null);
                        }
                    }
                }
            }
        }
        return objectList.toArray();
    }
}
