package com.example.Preclaimupdate.controller.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Audit_case_movement;

@Repository
public interface Audit_casemovementRepository extends JpaRepository<Audit_case_movement, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO audit_case_movement SELECT * from case_movement where caseId = :caseId", 
			nativeQuery = true)
	@Transactional
	void insertlog(@Param("caseId") int caseId);
	
	@Query(value="select caseId from audit_case_movement where fromId = :username", nativeQuery = true)
	<List>Audit_case_movement getListofCaseId(@Param("username")String username);

}
