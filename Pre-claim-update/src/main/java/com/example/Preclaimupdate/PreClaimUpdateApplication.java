package com.example.Preclaimupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.Preclaimupdate.common.MailCheck;
import com.example.Preclaimupdate.controller.Repository.MailConfigRepository;
import com.example.Preclaimupdate.entity.Mail_config;

@SpringBootApplication
public class PreClaimUpdateApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PreClaimUpdateApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PreClaimUpdateApplication.class);
	}

}
