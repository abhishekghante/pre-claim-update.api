package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "case_movement")
@Table(name = "case_movement")
public class Case_movement {

	public Case_movement() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int caseId;
	@Column
	private String fromId;
	@Column
	private String toId;
	@Column
	private String caseStatus;
	@Column
	private String remarks;
	@Column
	private Date createdDate;
	@Column
	private Date updatedDate;

	public Case_movement(int caseId, String fromId, String toId, String caseStatus, String remarks, Date createdDate,
			Date updatedDate) {
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
