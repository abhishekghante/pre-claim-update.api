package com.example.Preclaimupdate.entity;

import java.util.Date;

public class Request {

	private String username;
	private String password;
	private String oldpassword;
	private String newpassword;
	private String Status;
	private int pagesize;
	private int pageNum;
	private int caseid;
	private String case_description;
	private Date date;
	private String latitude;
	private String longitude;
	private String file;
	private String capturedDate;

	public Request() {
		super();
	}

	public Request(String username, String password, String oldpassword, String newpassword, 
			String status, int pagesize, int caseid, String case_description, Date date, String latitude, 
			String longitude, String file) 
	{
		this.username = username;
		this.password = password;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
		this.Status = status;
		this.pagesize = pagesize;
		this.caseid = caseid;
		this.case_description = case_description;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.file = file;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCaseid() {
		return caseid;
	}

	public void setCaseid(int caseid) {
		this.caseid = caseid;
	}

	public String getCase_description() {
		return case_description;
	}

	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCapturedDate() {
		return capturedDate;
	}

	public void setCapturedDate(String capturedDate) {
		this.capturedDate = capturedDate;
	}

	
}
