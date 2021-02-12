package com.example.Preclaimupdate.controller.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Case_lists;

@Repository
public interface CaselistsRepository extends JpaRepository<Case_lists, Integer>{
	
	Case_lists findByCaseId(int CaseId);
	
	@Query(value="SELECT TOP (:size) caseId,policynumber,investigationid,insuredname,insureddod,insureddob,sumassured,intimationtype,locationid,\r\n" + 
			"casestatus,nominee_name,nominee_ContactNumber,nominee_address,insured_address,case_description\r\n" + 
			"   FROM Case_lists where case_status =:caseStatus and updated_By =:u ;", nativeQuery = true)
	List<Case_lists> getCaselists(@Param("size")int size,@Param("caseStatus")String caseStatus,@Param("u")String updatedby);
	
	Case_lists save(Case_lists Case_lists);
	
	@Query(value="select * from Case_lists where intimation_type in('PIV','PRV','LIVE');", nativeQuery = true)
	List<Case_lists> findall();
	
	
	
}
