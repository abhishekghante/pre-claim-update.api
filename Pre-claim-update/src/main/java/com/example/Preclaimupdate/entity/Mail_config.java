package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Mail_config")
public class Mail_config {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mailConfigId")
	private int mailConfigId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "outgoingServer")
	private String outgoingServer;
	
	@Column(name = "outgoingPort")
	private int outgoingPort;
	
	@Column(name = "encryptionType")
	private String encryptionType;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "created_on")
	private Date created_on;
	
	@Column(name = "updatedBy")
	private String updatedBy;
	
	@Column(name = "updated_on")
	private Date updated_on;

	public Mail_config() {
		this.mailConfigId = 0;
		this.username = "";
		this.password = "";
		this.outgoingServer = "";
		this.outgoingPort = 0;
		this.encryptionType = "";
		this.status = 0;
		this.createdBy = "";
		this.created_on = new Date();
		this.updatedBy = "";
		this.updated_on = new Date();

	}

	public Mail_config(int mailConfigId, String username, String password, String outgoingServer, int outgoingPort,
			String encryptionType, int status, String createdBy, Date created_on, String updatedBy, Date updated_on) {
		super();
		this.mailConfigId = mailConfigId;
		this.username = username;
		this.password = password;
		this.outgoingServer = outgoingServer;
		this.outgoingPort = outgoingPort;
		this.encryptionType = encryptionType;
		this.status = status;
		this.createdBy = createdBy;
		this.created_on = created_on;
		this.updatedBy = updatedBy;
		this.updated_on = updated_on;
	}

	public int getMailConfigId() {
		return mailConfigId;
	}

	public void setMailConfigId(int mailConfigId) {
		this.mailConfigId = mailConfigId;
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

	public String getOutgoingServer() {
		return outgoingServer;
	}

	public void setOutgoingServer(String outgoingServer) {
		this.outgoingServer = outgoingServer;
	}

	public int getOutgoingPort() {
		return outgoingPort;
	}

	public void setOutgoingPort(int outgoingPort) {
		this.outgoingPort = outgoingPort;
	}

	public String getEncryptionType() {
		return encryptionType;
	}

	public void setEncryptionType(String encryptionType) {
		this.encryptionType = encryptionType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

}
