package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_log")
public class Activity_log {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int logId;
	@Column
	private String moduleName;
	@Column
	private String moduleCode;
	@Column
	private String moduleAction;
	@Column
	private String user_name;
	@Column
	private Date logDate;

	public Activity_log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activity_log(int logId, String moduleName, String moduleCode, String moduleAction, String user_name,
			Date logDate) {
		super();
		this.logId = logId;
		this.moduleName = moduleName;
		this.moduleCode = moduleCode;
		this.moduleAction = moduleAction;
		this.user_name = user_name;
		this.logDate = logDate;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleAction() {
		return moduleAction;
	}

	public void setModuleAction(String moduleAction) {
		this.moduleAction = moduleAction;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

}
