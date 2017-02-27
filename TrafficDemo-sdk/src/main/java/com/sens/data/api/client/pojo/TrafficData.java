package com.sens.data.api.client.pojo;

import java.util.Date;

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

    private String id;
	
	private String siteId;
    private String siteName;
    private String dataValue;
    private Date dateAndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date getDateAndTime() {

        return this.dateAndTime == null ? null : (Date) this.dateAndTime
                .clone();
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = (Date) dateAndTime.clone();
    }

    @Override
    public String toString() {
        return "TrafficData [siteName=" + siteName + ", siteId=" + siteId +", dataValue=" + dataValue
                + ", creationDate=" + dateAndTime + "]";
    }

}
