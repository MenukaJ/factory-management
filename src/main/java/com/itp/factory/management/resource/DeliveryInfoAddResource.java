package com.itp.factory.management.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class DeliveryInfoAddResource {
		
	@NotBlank(message = "{common.not-null}")
	@Size(max = 150, message = "{common-name.size}")
	private String description;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "", message = "{common-numeric.pattern}")
	private double amount;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "", message = "{common-numeric.pattern}")
	private String qty;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
