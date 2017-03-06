package com.sens.data.api.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "Traffic_Report")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficReport {
	
	@Id
    private String id;
	
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date recordTime;
    
    private List<TrafficData> trafficData;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date getRecordTime() {

        return this.recordTime == null ? null : (Date) this.recordTime
                .clone();
    }
    public void setRecordTime(Date recordTime) {
		this.recordTime = (Date) recordTime.clone();
	}
	public List<TrafficData> getTrafficData() {
		return trafficData;
	}

	public void setTrafficData(List<TrafficData> trafficData) {
		this.trafficData = trafficData;
	}

	@Override
	public String toString() {
		return "TrafficReport [recordTime=" + recordTime + ", trafficData=" + trafficData +"]";
	}
}
