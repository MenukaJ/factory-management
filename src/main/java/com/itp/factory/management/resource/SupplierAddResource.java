package com.itp.factory.management.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Supplier Add Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SupplierAddResource {
	
	@NotBlank(message = "{common.not-null}")
	@Size(max = 90, message = "{common-name.size}")
	private String name;
	
	@NotBlank(message = "{common.not-null}")
	@Size(max = 150, message = "{common-name.size}")
	private String address;
	
	
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-10]+", message = "{common-numeric.pattern}")
	private String contact;



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
	
}
