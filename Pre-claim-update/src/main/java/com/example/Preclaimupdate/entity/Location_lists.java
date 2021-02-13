package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "location_lists")
@Table(name = "location_lists")
public class Location_lists {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int locationId;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String zone;
	
	@JsonIgnore
	@Transient
	private String createdBy;
	
	@JsonIgnore
	@Transient
	private Date createdDate;
	
	@JsonIgnore
	@Transient
	private Date updatedDate;
	
	@JsonIgnore
	@Transient
	private String updatedBy;
	
	@JsonIgnore
	@Transient
	private int status;

	public Location_lists()
	{
		this.locationId = 0;
		this.city = "";
		this.state = "";
		this.zone = "";
	}
	
	public Location_lists(int locationId, String city, String state, String zone, String createdBy, Date createdDate,
			Date updatedDate, String updatedBy, int status) {
		super();
		this.locationId = locationId;
		this.city = city;
		this.state = state;
		this.zone = zone;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.status = status;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
