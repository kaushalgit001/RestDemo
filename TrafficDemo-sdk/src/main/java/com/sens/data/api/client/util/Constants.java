package com.sens.data.api.client.util;

/**
 * Holds all constants. Make sure the constant name in all upper case letters
 * with underscores for spaces.
 * 
 * @author kaushal
 */
public class Constants {

	public static final String DEFAULT_LOCAL_ROOT_URL = "http://localhost:1210";
	public static final String DEFAULT_DEV_ROOT_URL = "https://api.abc.com:portNo";  // dummy vm url:port
	public static final String DEFAULT_PROD_ROOT_URL = "https://api.abc.com:portNo"; // dummy vm url:port
	

	public static final String TRAFFIC_DATA_SERVICE_PATH = "/sens/v1";
	
	public static final String USER_INFO = "UserInfo";
	public static final String FETCH= "/fetch";
	public static final String FORWARD_SLASH= "/";

	public enum Environment {
		LOCAL, DEV, PROD
	}

	public enum Status {
		SUCCESS, FAILURE, HOST_NOT_FOUND
	}

	public enum HttpMethod {
		GET, POST, PUT, DELETE
	}
}
