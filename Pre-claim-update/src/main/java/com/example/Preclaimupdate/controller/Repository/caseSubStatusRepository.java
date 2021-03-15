package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.CaseSubStatus;
import com.example.Preclaimupdate.entity.Case_lists;


@Repository
public interface caseSubStatusRepository extends JpaRepository<CaseSubStatus, Integer>{

	
	CaseSubStatus findById(int id);
	
	
}
