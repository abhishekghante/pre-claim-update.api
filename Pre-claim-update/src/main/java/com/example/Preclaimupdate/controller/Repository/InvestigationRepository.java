package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Investigation_type;

@Repository
public interface InvestigationRepository extends JpaRepository<Investigation_type, Integer>{
	

	Investigation_type findByInvestigationId(int investigationId);
	

}
