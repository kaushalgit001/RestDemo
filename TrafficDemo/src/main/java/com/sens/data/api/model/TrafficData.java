package com.sens.data.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is POJO class acts as Model layer, which maps to the "TrafficData"
 * collection in DB
 * 
 * @author kaushal
 */

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficData {
	
    private String siteName;
	private String lat;
	private String lon;

	private TrafficBusiness trafficBusinessA;
	private TrafficSpeed trafficSpeedA;
	private TrafficBusiness trafficBusinessB;
	private TrafficSpeed trafficSpeedB;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public TrafficBusiness getTrafficBusinessA() {
		return trafficBusinessA;
	}

	public void setTrafficBusinessA(TrafficBusiness trafficBusinessA) {
		this.trafficBusinessA = trafficBusinessA;
	}

	public TrafficSpeed getTrafficSpeedA() {
		return trafficSpeedA;
	}

	public void setTrafficSpeedA(TrafficSpeed trafficSpeedA) {
		this.trafficSpeedA = trafficSpeedA;
	}

	public TrafficBusiness getTrafficBusinessB() {
		return trafficBusinessB;
	}

	public void setTrafficBusinessB(TrafficBusiness trafficBusinessB) {
		this.trafficBusinessB = trafficBusinessB;
	}

	public TrafficSpeed getTrafficSpeedB() {
		return trafficSpeedB;
	}

	public void setTrafficSpeedB(TrafficSpeed trafficSpeedB) {
		this.trafficSpeedB = trafficSpeedB;
	}

	@Override
	public String toString() {
		return "TrafficData[lat=" + lat + ", lon=" + lon + ",siteName="
				+ siteName + ", trafficBusinessA=" + trafficBusinessA
				+ ", trafficSpeedA=" + trafficSpeedA + ", trafficBusinessB="
				+ trafficBusinessB + ", trafficSpeedB=" + trafficSpeedB + "]";
	}

}
