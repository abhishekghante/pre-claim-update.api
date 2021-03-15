package com.example.Preclaimupdate.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.example.Preclaimupdate.controller.Repository.MailConfigRepository;
import com.example.Preclaimupdate.entity.Mail_config;

@ConfigurationProperties("spring.mail")
public class MailCheck {
	

	
	private String host;
	private int port;
	private String username;
	private String password;

	public MailCheck(String host, int port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public MailCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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

	@Override
	public String toString() {
		return "MailCheck [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	
	
	


}
