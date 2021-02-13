package com.example.Preclaimupdate.controller.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Case_lists;

@Repository
public interface CaselistsRepository extends JpaRepository<Case_lists, Integer>{
	
	Case_lists findByCaseId(int caseId);
	
	@Query(value = "SELECT D.* FROM case_lists D WHERE D.caseId IN (SELECT B.caseId FROM "
			+ "(SELECT ROW_NUMBER() OVER(ORDER BY A.caseId) AS RRN , A.caseId from case_lists A "
			+ "WHERE A.caseId in (SELECT caseId from case_movement where toId = :username)) B "
			+ "WHERE B.RRN BETWEEN :min AND :max) ", nativeQuery = true)
	List<Case_lists> getCaselists(@Param("username")String username, 
			@Param("min")int min, @Param("max")int max);
	
	Case_lists save(Case_lists Case_lists);
	
	@Query(value="select * from Case_lists where intimation_type in('PIV','PRV','LIVE');", nativeQuery = true)
	List<Case_lists> findall();
	
	
	
}
