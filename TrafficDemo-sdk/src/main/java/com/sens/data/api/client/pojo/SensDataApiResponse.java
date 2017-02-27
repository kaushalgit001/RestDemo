package com.sens.data.api.client.pojo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author kaushal
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensDataApiResponse {

    private JSONObject data;
    private JSONArray items;
    private Boolean boolData;

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONArray getItems() {
        return items;
    }

    public void setItems(JSONArray items) {
        this.items = items;
    }
    
    /**
	 * @return the boolData
	 */
	public Boolean getBoolData() {
		return boolData;
	}

	/**
	 * @param boolData the boolData to set
	 */
	public void setBoolData(Boolean boolData) {
		this.boolData = boolData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SensDataApiResponse [data=");
		builder.append(data);
		builder.append(", items=");
		builder.append(items);
		builder.append(", boolData=");
		builder.append(boolData);
		builder.append("]");
		return builder.toString();
	}

}
