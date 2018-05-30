package com.zzx.common;

import java.util.Date;

public class KafkaMessage {
	
	
	public KafkaMessage() {}
	
	public KafkaMessage(String key,String value,Date date) {
		
		this.key = key;
		this.value = value;
		this.createDate = date;
	}
	
	

	private String key;
	
	private String value;
	
	private Date createDate;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
