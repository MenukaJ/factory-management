package com.itp.factory.management.responcehandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommonResponseHandler {
	@JsonProperty("messages")
	private String messages;
	
	@JsonProperty("details")
	private String details;
	
	@JsonProperty("value")
	private String value;
	
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public CommonResponseHandler() {
		super();
	}
	public CommonResponseHandler(String messages) {
		super();
		this.messages = messages;
	}

	public CommonResponseHandler(String messages, String value) {
		super();
		this.messages = messages;
		this.value = value;
	}
	
}
