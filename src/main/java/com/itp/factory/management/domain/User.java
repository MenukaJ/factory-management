package com.itp.factory.management.domain;

import com.itp.factory.management.core.BaseEntity;
import com.itp.factory.management.enums.CommonStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Category Domain
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                            MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 0000000000001;
	
	@Column(name = "type")
	private String type;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "dob")
	private String dob;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "leavesremain")
	private String leavesremain;

	@Column(name = "joineddate")
	private String joineddate;

	@Column(name = "salary")
	private String salary;

	@Column(name = "status")
	private String status;

	@Column(name = "created_user")
	private String createdUser;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "modified_user")
	private String modifiedUser;
	
	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLeavesremain() {
		return leavesremain;
	}

	public void setLeavesremain(String leavesremain) {
		this.leavesremain = leavesremain;
	}

	public String getJoineddate() {
		return joineddate;
	}

	public void setJoineddate(String joineddate) {
		this.joineddate = joineddate;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
