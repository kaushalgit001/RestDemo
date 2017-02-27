package com.sens.data.api.service;

import com.sens.data.api.model.TrafficData;
import com.sens.data.api.pojo.ApiResponse;

/**
 * This is an interface, Define all the methods implemented in
 * TrafficDataServiceImpl class which acts as a Repository.
 * 
 * @author kaushal
 */
public interface TrafficDataService {

    /**
     * Method to Insert Data In Traffic_Data collection.
     * 
     * @param trafficData
     * @return
     */
    ApiResponse insertTrafficData(TrafficData trafficData);
    
    /**
     * Method to Fetch All the Data from Traffic_Data collections.
     * 
     * @return
     */

    ApiResponse fetchAllTrafficData();

    /**
     * Method to fetch Data by provided fetchValue("siteId,siteName") from
     * Traffic_Data collections.
     * 
     * @param fetchValue
     * @return
     */
    ApiResponse fetchBySiteIdAndSiteName(String fetchValue);

}
