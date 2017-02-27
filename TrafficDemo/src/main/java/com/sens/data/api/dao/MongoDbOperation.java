package com.sens.data.api.dao;

import static com.sens.data.api.constant.Constants.SITE_ID;
import static com.sens.data.api.constant.Constants.SITE_NAME;
import static com.sens.data.api.constant.Constants.TRAFFIC_COLLECTION;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sens.data.api.model.TrafficData;
import com.sens.data.api.util.CommonUtil;

/**
 * Implementation class of service interface. Its is a DAO layer provides the
 * methods for communicating to DB.
 * 
 * @author kaushal
 */
@Service
public class MongoDbOperation {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MongoDbOperation.class);

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Method to Insert Data In Traffic_Data collection.
     * 
     * @param trafficData
     * @return
     */
    public TrafficData insertTrafficData(TrafficData trafficData) {

        LOGGER.info("Insert in collection " + TRAFFIC_COLLECTION);
       try {
    	   trafficData.setDateAndTime(new Date());
            mongoTemplate.save(trafficData, TRAFFIC_COLLECTION);
            return trafficData;
        } catch (Exception e) {
            LOGGER.info("Fail to insert", e);
            return null;
        }

    }

    /**
     * Method to fetch List from Traffic_Data collections.
     * 
     * @return
     */

    public List<TrafficData> fetchAllTrafficInfo() {

        LOGGER.info("Fetch all data from collection " + TRAFFIC_COLLECTION);
        try {
            return mongoTemplate.findAll(TrafficData.class, TRAFFIC_COLLECTION);
        } catch (Exception e) {

            LOGGER.info("Fail to fetchAll TrafficInfo data", e);
            return null;
        }
    }

    /**
     * Method to fetch from Traffic_Data collections based on siteId and siteName.
     * 
     * @param siteId
     * @param siteName
     * @return
     */
    public TrafficData fetchBySiteIdSiteName(String siteId, String siteName) {

        LOGGER.info("Fetch data by siteId and siteName from collection "
                + TRAFFIC_COLLECTION);
        Query query;
        query = new Query().addCriteria(Criteria.where(SITE_ID)
                .is(siteId).and(SITE_NAME).is(siteName));
        try {
            return mongoTemplate.findOne(query, TrafficData.class,
            		TRAFFIC_COLLECTION);
        } catch (Exception e) {
            LOGGER.info("Fail to fetch Data by siteId , siteName ", e);
            return null;
        }

    }
}
