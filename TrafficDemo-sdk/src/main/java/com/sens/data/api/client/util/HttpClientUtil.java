package com.sens.data.api.client.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sens.data.api.client.pojo.HttpResponseEntity;

/**
 * Util class required to invoke REST web services. (Responsible to make HTTP
 * requests)
 *
 * @author kaushal
 */
public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(HttpClientUtil.class);
    
    private static HttpClientUtil httpClientUtil = null;

    private HttpClientUtil() {

    }

    public static HttpClientUtil getInstance() {
        if (httpClientUtil == null) {
            httpClientUtil = new HttpClientUtil();
        }
        return httpClientUtil;
    }

    public HttpResponseEntity invokeHttpRequest(HttpMessage request) {
        return invokeHttpRequest(request, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * Invokes a HTTP request, the requestBody attribute should be null for GET
     * and DELETE
     *
     * @param request
     * @param requestBody
     * @param authStringEnc
     * @return
     */
    public HttpResponseEntity invokeHttpRequest(HttpMessage request,
                                                       String requestBody, String authStringEnc) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponseEntity responseEntity = new HttpResponseEntity();
        HttpResponse response;
        try {

            if (request != null) {
                request.setHeader("Authorization", "Basic " + authStringEnc);
                request.addHeader("content-type", "application/json");
            }

            if (request instanceof HttpPost || request instanceof HttpPut) {
                StringEntity params = new StringEntity(requestBody);
                ((HttpEntityEnclosingRequestBase) request).setEntity(params);
            }

            response = httpClient.execute((HttpUriRequest) request);
            responseEntity.setStatusCode(response.getStatusLine()
                    .getStatusCode());
            if (response.getEntity() != null) {
                responseEntity.setEntity(EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
        } catch (IOException ex) {
            LOGGER.error("An error occurred:\n", ex);
            responseEntity.setStatusCode(HttpStatus.SC_NOT_FOUND);
       } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("Error(s) occurred:\n", e);
            }
        }
        return responseEntity;
    }
}