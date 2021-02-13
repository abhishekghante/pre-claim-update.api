package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class User_role {

	public User_role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int roleId;
	@Column
	private String role;
	@Column
	private String role_code;
	@Column
	private int status;
	@Column
	private Date created_on;
	@Column
	private Date updated_on;

	public User_role(int roleId, String role, String role_code, int status, Date created_on, Date updated_on) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.role_code = role_code;
		this.status = status;
		this.created_on = created_on;
		this.updated_on = updated_on;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

}
