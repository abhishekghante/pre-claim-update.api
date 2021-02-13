package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intimation_type")
public class Intimation_type {

	public Intimation_type() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int intimationTypeId;
	@Column
	private String intimationTypeName;
	@Column
	private String createdBy;
	@Column
	private Date createdDate;
	@Column
	private Date updatedDate;
	@Column
	private String updatedBy;
	@Column
	private int status;

	public Intimation_type(int intimationTypeId, String intimationTypeName, String createdBy, Date createdDate,
			Date updatedDate, String updatedBy, int status) {
		super();
		this.intimationTypeId = intimationTypeId;
		this.intimationTypeName = intimationTypeName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.status = status;
	}

	public int getIntimationTypeId() {
		return intimationTypeId;
	}

	public void setIntimationTypeId(int intimationTypeId) {
		this.intimationTypeId = intimationTypeId;
	}

	public String getIntimationTypeName() {
		return intimationTypeName;
	}

	public void setIntimationTypeName(String intimationTypeName) {
		this.intimationTypeName = intimationTypeName;
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
