package com.sens.data.api.client.util;

/**
 * Defines helper/util methods that performs common functionality which doesn't
 * depend on the state of the object.
 * 
 * @author kaushal
 */
public class Util {

    private Util() {
    }

    /**
     * Determines whether the given string is a null or an empty string
     * 
     * @param value
     *            - input string
     * @return - true or false
     */
    public static Boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0
                || "null".equalsIgnoreCase(value);
    }

    /**
     * Constructs service API URLs based on different parameter.
     *
     * @param baseUrl
     * @param param
     * @return
     */

    public static String constructUrl(String baseUrl, String fetchval) {

        String url = "";
        if (!Util.isNullOrEmpty(fetchval) && fetchval.contains(",")) {

            url = baseUrl + "?filter-by=fName,lName&filter-value=" + fetchval;
        } else {

            url = baseUrl;
        }

        return url;
    }

}
