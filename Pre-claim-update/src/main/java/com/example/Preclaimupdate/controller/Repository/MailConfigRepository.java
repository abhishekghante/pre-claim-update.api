package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Preclaimupdate.entity.Activity_log;
import com.example.Preclaimupdate.entity.Case_movement;
import com.example.Preclaimupdate.entity.Mail_config;

public interface MailConfigRepository extends JpaRepository<Mail_config, Integer> {
	
	Mail_config findBymailConfigId(int caseId);

	
	
}
