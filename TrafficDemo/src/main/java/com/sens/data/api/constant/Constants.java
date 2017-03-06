package com.sens.data.api.constant;

/**
 * Holds all constants. Make sure the constant name in all upper case letters
 * with underscores for spaces.
 * 
 * @author kaushal
 */
public interface Constants {

    enum STATUS {
        SUCCESS, ERROR, INVALID_REQUEST;
    }

    String DB_ERROR = "Data unavailable";
    String DB_INSERTION_ERROR = "Data Insertion problem";
    String DB_UPDATE_ERROR = "Data Updation problem";
    String DB_DELETE_ERROR = "Data Delete problem";
    String DB_FETCH_ERROR = "Data Fetch problem";
    String SITE_ID = "siteId";
    String SITE_NAME = "siteName";
    // Collection name
    String TRAFFIC_COLLECTION = "Traffic_Report";
    // class name
    String TRAFFIC_REPORT = "TrafficReport";

}
