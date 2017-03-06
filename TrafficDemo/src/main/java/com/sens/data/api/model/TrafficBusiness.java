package com.sens.data.api.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficBusiness {

	private String value;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date time;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getTime() {

		return this.time == null ? null : (Date) this.time.clone();
	}

	public void setRecordTime(Date time) {
		this.time = (Date) time.clone();
	}

	@Override
	public String toString() {
		return "TrafficBusiness [value=" + value + ", time=" + time + "]";
	}
}
