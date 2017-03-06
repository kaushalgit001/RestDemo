package com.sens.data.api.service;

import static com.sens.data.api.constant.Constants.DB_FETCH_ERROR;
import static com.sens.data.api.constant.Constants.DB_INSERTION_ERROR;
import static com.sens.data.api.constant.Constants.DB_UPDATE_ERROR;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.sens.data.api.constant.Constants.STATUS;
import com.sens.data.api.dao.MongoDbOperation;
import com.sens.data.api.model.TrafficData;
import com.sens.data.api.model.TrafficReport;
import com.sens.data.api.pojo.ApiResponse;
import com.sens.data.api.util.CommonUtil;

/**
 * This is a Repository. It implements All the method listed in UserdataService
 * 
 * @author kaushal
 */
@Repository
public class TrafficDataServiceImpl implements TrafficDataService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(TrafficDataServiceImpl.class);

    @Autowired
    MongoDbOperation mongoDbOperation;
    
    /**
     * Method to Insert Data In Traffic_Data collection.
     * 
     * @param trafficData
     * @return
     */
    @Override
    public ApiResponse insertTrafficData(TrafficReport trafficData) {

        LOGGER.info("Inserting Data to Traffic_Data DB " +trafficData);
        TrafficReport responseFrmUserInfo = mongoDbOperation
                .insertTrafficData(trafficData);

        if (null == responseFrmUserInfo) {

            LOGGER.info("Data can not insert, Response from User_Info is null :");
            return CommonUtil.createApiResponseFail(STATUS.ERROR, null,
                    HttpStatus.BAD_REQUEST.value(), DB_INSERTION_ERROR);
        }

        LOGGER.info("Successfully inserted the data ", STATUS.SUCCESS);
        return CommonUtil
                .createApiResponse(STATUS.SUCCESS, responseFrmUserInfo);
    }

    /**
     * Method to Fetch All the Data from Traffic_Data collections.
     * @return
     *//*
    @Override
    public ApiResponse fetchAllTrafficData() {

        LOGGER.info("Fetching All the Data from Traffic_Data");
        List<TrafficData> allTrafficInfo = mongoDbOperation.fetchAllTrafficInfo();

        if (allTrafficInfo.isEmpty()) {

            LOGGER.info("There is no Data available ");
            return CommonUtil.createApiResponseFail(STATUS.ERROR, null,
                    HttpStatus.BAD_REQUEST.value(), DB_UPDATE_ERROR);
        }

        LOGGER.info("Successfully fetched the data ", STATUS.SUCCESS);
        return CommonUtil.createApiResponse(STATUS.SUCCESS, allTrafficInfo);

    }

    *//**
     * Method to fetch Data by siteId and siteName In Traffic_Data collections.
     * 
     * @param fetchValue
     * @return
     *//*
    @Override
    public ApiResponse fetchBySiteIdAndSiteName(String fetchValue) {

        String siteId = fetchValue.split(",")[0].trim();
        String siteName = fetchValue.split(",")[1].trim();

        LOGGER.info("Fetch data By siteId & siteName !!");
        TrafficData trafficData = mongoDbOperation.fetchBySiteIdSiteName(siteId,
        		siteName);

        if (trafficData == null) {
            LOGGER.info("data does not exist corresponding to siteId and siteName entered !!");
            return CommonUtil.createApiResponseFail(STATUS.ERROR, null,
                    HttpStatus.BAD_REQUEST.value(), DB_FETCH_ERROR);
        }

        LOGGER.info("Successfully Fetched data by siteId and siteName ",
                STATUS.SUCCESS);
        return CommonUtil.createApiResponse(STATUS.SUCCESS, trafficData);

    }
*/
}
