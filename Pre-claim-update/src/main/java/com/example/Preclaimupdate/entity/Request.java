package com.example.Preclaimupdate.entity;

import java.util.Date;

public class Request {

	private String username;
	private String password;
	private String oldpassword;
	private String newpassword;
	private String Status;
	private int pagesize;
	private int caseid;
	private String description;
	private Date date;
	private int lat;
	private int longi;
	private String file;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(String username, String password, String oldpassword, String newpassword, String status,
			int pagesize, int caseid, String description, Date date, int lat, int longi, String file) {
		super();
		this.username = username;
		this.password = password;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
		Status = status;
		this.pagesize = pagesize;
		this.caseid = caseid;
		this.description = description;
		this.date = date;
		this.lat = lat;
		this.longi = longi;
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

	public int getCaseid() {
		return caseid;
	}

	public void setCaseid(int caseid) {
		this.caseid = caseid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLongi() {
		return longi;
	}

	public void setLongi(int longi) {
		this.longi = longi;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
