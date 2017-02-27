package com.sens.data.api.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sens.data.api.constant.Constants;
import com.sens.data.api.model.TrafficData;
import com.sens.data.api.pojo.ApiResponse;

/**
 * Defines helper/util methods that performs common functionalities which
 * doesn't depend on the state of the object.
 *
 * @author kaushal
 */
public class CommonUtil {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CommonUtil.class);

    /* Private Constructor for CommonUtil classe */
    private CommonUtil() {
    }

    /* Returning a object of CommonsUtil class */
    public static CommonUtil getInstance() {
        return new CommonUtil();
    }

    /**
     * Util method to construct API response object
     *
     * @param status
     * @param data
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static ApiResponse createApiResponseFail(Constants.STATUS status,
            Object data, Integer errorCode, String errorMessage) {
        ApiResponse response = new ApiResponse();
        response.setStatus(status.name());
        response.setData(data);
        response.setError(errorCode, errorMessage);
        LOGGER.info("Response from API: {}", response.toString());
        return response;
    }

    /**
     * Util method to construct API response object
     *
     * @param status
     * @param data
     * @return
     */
    public static ApiResponse createApiResponse(Constants.STATUS status,
            Object data) {
        ApiResponse response = new ApiResponse();
        response.setStatus(status.name());
        response.setData(data);
        LOGGER.info("API Response : {}", response.toString());
        return response;
    }
    
    /**
     * Decrypts the password using the Jasypt library
     *
     * @param encrypted  password, key
     * @return decrypted password
     */
    public static String decrypt(String password, String key) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        String encryptedPassword = null;
        encryptor.setPassword(key);
        try {
            encryptedPassword = encryptor.decrypt(password);
        } catch (Exception e) {
        	
        	e.printStackTrace();
        }
        return encryptedPassword;
    }

}
