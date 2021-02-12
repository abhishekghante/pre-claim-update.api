package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "investigation_type")
public class Investigation_type {

	public Investigation_type() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int investigationId;
	@Column
	private String investigationType;
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

	public Investigation_type(int investigationId, String investigationType, String createdBy, Date createdDate,
			Date updatedDate, String updatedBy, int status) {
		super();
		this.investigationId = investigationId;
		this.investigationType = investigationType;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.status = status;
	}

	public int getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(int investigationId) {
		this.investigationId = investigationId;
	}

	public String getInvestigationType() {
		return investigationType;
	}

	public void setInvestigationType(String investigationType) {
		this.investigationType = investigationType;
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

	@Override
	public String toString() {
		return "Investigation_type [investigationId=" + investigationId + ", investigationType=" + investigationType
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", status=" + status + "]";
	}
	
	

}
