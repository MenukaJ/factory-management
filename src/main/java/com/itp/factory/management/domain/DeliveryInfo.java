package com.itp.factory.management.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * DeliveryInfo Domain
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   06-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

@Entity
@Data
@Table(name = "DeliveryInfo")
public class DeliveryInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Serial_No")
	private long id;
	
	@Column(name = "Sp_Id")
	private long sid;
	
	@Column(name = "Supplier_Name")
	private String name;
	
	@Column(name = "Vehicle_No")
	private String vehicleNo;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Loading_Place")
	private String loadingPlace;
	
	@Column(name = "Delivery_Date")
	private Timestamp deliveryDate;
	
	@Column(name = "Materials")
	private String materials;
	
	@Column(name = "Quantity")
	private int qty;
	
	@Column(name = "Unit_Price")
	private double price;
	
	@Column(name = "Amount")
	private double amount;
	
	@Column(name = "Rate")
	private double rate;
	
	@Column(name = "Remarks")
	private String remarks;
	
	

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public double getAmount() {
		amount = price * qty;
		return amount;
		
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}
	

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLoadingPlace() {
		return loadingPlace;
	}

	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = loadingPlace;
	}

	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
	
}
