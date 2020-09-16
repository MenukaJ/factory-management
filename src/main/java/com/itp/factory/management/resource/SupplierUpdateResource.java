package com.itp.factory.management.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * Supplier Update Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SupplierUpdateResource {
	
	private String id;
	@NotBlank(message = "{common.not-null}")
	@Size(max = 70, message = "{common-name.size}")
	private String name;
	
	@Size(max = 255, message = "{description.size}") 
	private String description;
	
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-10]+", message = "{common-numeric.pattern}")
	private String contact;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


}
