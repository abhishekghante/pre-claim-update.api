package com.example.Preclaimupdate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "case_substatus")
@Table(name = "case_substatus")
public class CaseSubStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "user_role")
	private String user_role;

	@Column(name = "Case_status")
	private String Case_status;

	@Column(name = "caseSubStatus")
	private String caseSubStatus;

	@Column(name = "level")
	private int level;

	CaseSubStatus() {
		this.id = 0;
		this.user_role = "";
		this.caseSubStatus = "";
		this.Case_status = "";
		this.level = 0;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getCase_status() {
		return Case_status;
	}

	public void setCase_status(String case_status) {
		Case_status = case_status;
	}

	public String getCaseSubStatus() {
		return caseSubStatus;
	}

	public void setCaseSubStatus(String caseSubStatus) {
		this.caseSubStatus = caseSubStatus;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "CaseSubStatus [id=" + id + ", user_role=" + user_role + ", Case_status=" + Case_status
				+ ", caseSubStatus=" + caseSubStatus + ", level=" + level + "]";
	}

}
