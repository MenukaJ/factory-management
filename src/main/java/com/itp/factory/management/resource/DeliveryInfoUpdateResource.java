package com.itp.factory.management.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * Common Add Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-09-2020          					Anushka     Created
 *    
 ********************************************************************************************************
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DeliveryInfoUpdateResource {

	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "", message = "{common-numeric.pattern}")
	private String amount;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "", message = "{common-numeric.pattern}")
	private String qty;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	
	
}
