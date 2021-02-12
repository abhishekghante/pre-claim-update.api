package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Audit_case_movement;
import com.example.Preclaimupdate.entity.Case_movement;

@Repository
public interface Audit_casemovementRepository extends JpaRepository<Audit_case_movement, Integer>{
	
	Audit_case_movement save(Audit_case_movement s);
	

}
