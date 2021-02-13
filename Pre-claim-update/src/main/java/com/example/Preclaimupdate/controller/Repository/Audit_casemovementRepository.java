package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Audit_case_movement;

@Repository
public interface Audit_casemovementRepository extends JpaRepository<Audit_case_movement, Integer>{
	
	@Query(value = "INSERT INTO audit_case_movement SELECT * from case_movement where caseId = :caseId", 
			nativeQuery = true)
	Audit_case_movement insertlog(@Param("caseId") int caseId);

}
