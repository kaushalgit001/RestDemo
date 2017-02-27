package com.sens.data.api.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sens.data.api.constant.Constants.*;
import static com.sens.data.api.constant.Parameter.FILTER_BY;
import static com.sens.data.api.constant.Parameter.FILTER_VALUE;
import static com.sens.data.api.constant.Parameter.SITEID_SITENAME;
import static com.sens.data.api.constant.Parameter.INSERT_TO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sens.data.api.model.TrafficData;
import com.sens.data.api.pojo.ApiResponse;
import com.sens.data.api.service.TrafficDataService;


//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * This is RootController class consists multiple methods , used to handle HTTP request.
 * @author Kaushal
 *
 */
@RestController
@RequestMapping(value = "/sens/v1")
public class RootController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
	
	@Autowired
    TrafficDataService trafficDataService;

	
	
	/**
     * test by echo message.
     * @return String
     */
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return str;
    }
    
    
    /**
     * Create information in collections.
     * Based on insert-to values, insert the data in respective collection. 
     * 
     * @param TrafficData
     * @return Create API response
     * @throws IOException
     */
    @RequestMapping(value = "/{" + INSERT_TO + "}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse create(@RequestBody String content,
            @PathVariable(value = INSERT_TO) String insertTo)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse apiResponse = new ApiResponse();

        if (insertTo.equalsIgnoreCase(TRAFFIC_DATA)) {

            TrafficData trafficData = mapper.readValue(content, TrafficData.class);
            LOGGER.info("Create operation with Request context:/sens/v1 Method: POST : "
                    + trafficData);
            apiResponse = trafficDataService.insertTrafficData(trafficData);
        }
        return apiResponse;

    }

        /**
     * Fetch user information from Traffic_Data collection based on filter criteria.
     * 
     * @param fetchBy
     * @param fetchValue
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse fetch(
            @RequestParam(value = FILTER_BY, required = false, defaultValue = "ALL") String fetchBy,
            @RequestParam(value = FILTER_VALUE, required = false) String fetchValue)
            throws IOException {

        if (fetchBy.equalsIgnoreCase(SITEID_SITENAME)) {

            LOGGER.info(
                    "Fetch by siteId and siteName with Request context:/sens/v1/fetch Method: GET",
                    fetchValue);
            return trafficDataService.fetchBySiteIdAndSiteName(fetchValue);

        }

        LOGGER.info("fetching All  Traffic_Data entry : {}");
        return trafficDataService.fetchAllTrafficData();
    }
	
	
}
