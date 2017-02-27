package com.sens.data.api.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sens.data.api.client.pojo.TrafficData;
import com.sens.data.api.client.util.Constants;
import com.sens.data.api.client.util.RestApiUtil;
import com.sens.data.api.client.util.Util;

/**
 * Service class to execute TrafficDataService operations. you can initialize this
 * class, by calling the overloaded parameterized constructor by passing the
 * Constants.Environment.* as constructor parameter for Environment(LOCAL, DEV,
 * PROD) respectively
 * 
 * @author kaushal
 */

public class SensDataService extends SensData {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SensDataService.class);
    private static final String RESULT = "result";

    private RestApiUtil restApiUtil;

    public SensDataService() {
        super();
        restApiUtil = RestApiUtil.getInstance();
    }

    public SensDataService(Constants.Environment env) {
        super(env);
        restApiUtil = RestApiUtil.getInstance();
    }

    public void setRestApiUtil(RestApiUtil restApiUtil) {
        this.restApiUtil = restApiUtil;
    }
    
    public static void main(String [] args){
        
        SensDataService objj = new SensDataService(Constants.Environment.LOCAL);
        
        try {
			List<TrafficData> list= objj.fetchAllTrafficData();
			
			//Iterator<TrafficData> it = list.iterator();
			for(TrafficData tt:list){
				
				System.out.println("==> "+tt.getSiteName());
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    

    /**
     *
     * Call create API, we must have to provide jsonPayload parameter.
     * 
     * @param trafficData
     * @return
     */
    public TrafficData create(TrafficData trafficData) throws IOException,
            RuntimeException {
        String insertTo = "TrafficData";
        String url = trafficDataServiceUrl + Constants.FORWARD_SLASH + insertTo;
        
        //String url = "http://localhost:8080/code/user/v1/TrafficData";// ***** check this should be the url

        LOGGER.info("Connecting the traffic-data-api to create the data with end point url "
                + url);
        LOGGER.info("Method: " + Constants.HttpMethod.POST.name());
        LOGGER.info("Payload: " + trafficData);

        return new ObjectMapper().readValue(restApiUtil.post(url, trafficData)
                .getData().toString(), TrafficData.class);
    }
    
    /**
    *
    * Call userInfo API for fetch All userData.
    * 
    * @return
    */
   public List<TrafficData> fetchAllTrafficData() throws IOException,
           RuntimeException {
       List<TrafficData> trafficDataList = null;

       String url = trafficDataServiceUrl + Constants.FETCH;
       LOGGER.info("Connecting the traffic-data-api to fetch the data in Traffic_Data collection using url is : "
               + url);
       LOGGER.info("Method: " + Constants.HttpMethod.GET.name());
       ObjectMapper mapper = new ObjectMapper();
       String data = mapper
               .writeValueAsString(restApiUtil.get(url).getItems());
       LOGGER.info(data);
       trafficDataList = mapper.readValue(data, TypeFactory.defaultInstance()
               .constructCollectionType(List.class, TrafficData.class));
       return trafficDataList;
   }
   
   /**
   *
   * Call senseData API for fetch trafficData, we must have to provide
   * filterValue, By providing filterValue we can fetch the specific site
   * data. The parameter must be a comma separated "siteId,siteName".
   * 
   * @param filterVal
   * @return
   */

  public TrafficData fetchOneTrafficData(String filterVal) throws IOException,
          RuntimeException {
	  TrafficData trafficData = null;
      String baseurl = trafficDataServiceUrl + Constants.FETCH;
      String url = Util.constructUrl(baseurl, filterVal);

      LOGGER.info("Connecting the sens-data-api to fetch the data in Traffic_Data collection using url is : "
              + url);
      LOGGER.info("Method: " + Constants.HttpMethod.GET.name());
      ObjectMapper mapper = new ObjectMapper();
      String data = mapper.writeValueAsString(restApiUtil.get(url.trim())
              .getData());
      LOGGER.info(data);
      trafficData = mapper.readValue(data, TrafficData.class);
      return trafficData;
  }

   
}
