package com.sens.data.api.client.util;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpMessage;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sens.data.api.client.pojo.HttpResponseEntity;
import com.sens.data.api.client.pojo.SensDataApiResponse;

/**
 * Helper class which provides generic functionality to invoke HTTP requests.
 *
 * @author kaushal
 */
public class RestApiUtil {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RestApiUtil.class);
    private static final String DATA = "data";
    private static final String ERROR = "error";
    private static RestApiUtil restApiUtil = null;
    private HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();

    public RestApiUtil() {

    }

    public static RestApiUtil getInstance() {
        if (restApiUtil == null) {
            restApiUtil = new RestApiUtil();
        }
        return restApiUtil;
    }

    public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
        this.httpClientUtil = httpClientUtil;
    }

    /**
     * Invokes Http GET request and returns response entity
     *
     * @param url
     * @return
     * @throws IOException
     * @throws RuntimeException
     */
    public SensDataApiResponse get(String url) throws IOException, RuntimeException {
        HttpMessage request = new HttpGet(url);
        HttpResponseEntity response = httpClientUtil.invokeHttpRequest(request);
        return parse(response);
    }
    
    
    /**
     * Invokes Http POST request and returns response entity
     *
     * @param url
     * @param data
     * @return
     * @throws IOException
     * @throws RuntimeException
     */
    public SensDataApiResponse post(String url, Object data) throws IOException, RuntimeException {
        HttpMessage request = new HttpPost(url);
        HttpResponseEntity response = httpClientUtil.invokeHttpRequest(request, new ObjectMapper().writeValueAsString(data), StringUtils.EMPTY);
        return parse(response);
    }

    /**
     * A simple utility method, parses HttpResponseEntity object and returns either "data" or "error" attributes of a response data
     *
     * @param response
     * @return
     * @throws IOException
     * @throws RuntimeException
     */
    public SensDataApiResponse parse(HttpResponseEntity response) throws IOException, RuntimeException {
        JSONObject result;
        JSONParser parser = new JSONParser();
        SensDataApiResponse sensApiResponse = new SensDataApiResponse();
        try {
            if (response != null) {
                result = (JSONObject) parser.parse(response.getEntity());
                
                switch (response.getStatusCode()) {
                    case HttpStatus.SC_OK: {
                        if (result.get(DATA) instanceof JSONObject) {
                        	sensApiResponse.setData((JSONObject) result.get(DATA));
                        }
                        if (result.get(DATA) instanceof JSONArray) {
                        	sensApiResponse.setItems((JSONArray) result.get(DATA));
                        }
                    }
                    break;
                    case HttpStatus.SC_BAD_REQUEST:
                        throw new IOException(result.get(ERROR).toString());
                    case HttpStatus.SC_NOT_FOUND:
                        throw new UnknownHostException(
                                Constants.Status.HOST_NOT_FOUND.name());
                    default:
                        throw new RuntimeException(result.get(ERROR).toString());
                }
            }
        } catch (ParseException ex) {
            LOGGER.error("An error occurred when invoking API call: {}", ex);
        }

        return sensApiResponse;
    }
}
