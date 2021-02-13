package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "audit_case_movement")
@Table(name = "audit_case_movement")
public class Audit_case_movement {

	@Id
	@Column(name = "caseId")
	private int caseId;
	
	@Column(name = "fromId")
	private String fromId;
	
	@Column(name = "toId")
	private String toId;
	
	@Column(name = "caseStatus")
	private String caseStatus;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updatedDate")
	private Date updatedDate;

	public Audit_case_movement() {
	}

	public Audit_case_movement(int caseId, String fromId, String toId, String caseStatus, String remarks,
			Date createdDate, Date updatedDate) {
		super();
		this.caseId = caseId;
		this.fromId = fromId;
		this.toId = toId;
		this.caseStatus = caseStatus;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	

}
