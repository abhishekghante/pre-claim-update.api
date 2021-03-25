package com.example.Preclaimupdate.common;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.example.Preclaimupdate.controller.Repository.MailConfigRepository;
import com.example.Preclaimupdate.entity.Mail_config;

@Component
public class MailCheck {
	
	@Autowired
	private MailConfigRepository mailConfig;
	
	
@Bean
public JavaMailSender getMailSender() {
	
	Mail_config mConfig =  mailConfig.findBymailConfigId(9);
	
	
	JavaMailSenderImpl mailsender= new JavaMailSenderImpl();
	
	  mailsender.setHost(mConfig.getOutgoingServer());
	  mailsender.setPort(mConfig.getOutgoingPort());
	  mailsender.setUsername(mConfig.getUsername());
	  mailsender.setPassword(mConfig.getPassword());
	  
	  Properties property= new Properties();
	  property.put("mail.smtp.starttls.enable", "true");
	  property.put("mail.smtp.auth", "true");
	  property.put("mail.transport.protocol", "smtp");
	  
	  mailsender.setJavaMailProperties(property);
	 
	return mailsender;
	
	
}
	
	
	
	
	
	


}
