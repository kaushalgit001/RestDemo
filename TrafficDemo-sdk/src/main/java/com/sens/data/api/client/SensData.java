package com.sens.data.api.client;

import com.sens.data.api.client.util.Constants;

/**
 * Initializer for the user data service to invoke the API end points for a given
 * environment.
 * 
 * @author kaushal
 */
public class SensData {
    
    protected final String trafficDataServiceUrl;
    
    public SensData(Constants.Environment env) {
        String rootUrl = null;
        switch (env) {
        case LOCAL:
            rootUrl = Constants.DEFAULT_LOCAL_ROOT_URL;
            break;
        case DEV:
            rootUrl = Constants.DEFAULT_DEV_ROOT_URL;
            break;
        case PROD:
            rootUrl = Constants.DEFAULT_PROD_ROOT_URL;
            break;
        }
        trafficDataServiceUrl = rootUrl + Constants.TRAFFIC_DATA_SERVICE_PATH;
    }
    
    /*Default API connection url will be production */
    public SensData() {
    	trafficDataServiceUrl = Constants.DEFAULT_PROD_ROOT_URL + Constants.TRAFFIC_DATA_SERVICE_PATH;
    }
    
    /*To get the url method */

    public String getUrl() {
        return trafficDataServiceUrl;
    }

}
