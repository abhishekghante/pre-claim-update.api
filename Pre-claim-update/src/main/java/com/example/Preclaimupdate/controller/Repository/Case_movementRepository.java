package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Case_movement;

@Repository
public interface Case_movementRepository extends JpaRepository<Case_movement, Integer>{
	
	Case_movement findByCaseId(long caseId);
	
	@Query(value="select count(*) from case_movement where toId = :username", nativeQuery = true)
	int getNewCaseCount(@Param("username")String username);
	
	@Query(value="select count(*) from case_lists a, ("
			+ "select a.* from audit_case_movement a, ("
			+ "select caseId, max(updatedDate) as updatedDate from audit_case_movement "
			+ "where user_role = 'INV' group by caseId) b "
			+ "where a.caseId = b.caseId and a.updatedDate = b.updatedDate and a.user_role = 'INV') b "
			+ "where a.caseId = b.caseId and b.toId = :username", nativeQuery = true)
	int getCaseSubmittedCount(@Param("username")String username);

	@Query(value="select count(*) from case_lists a, ("
			+ "select a.* from audit_case_movement a, ("
			+ "select caseId, max(updatedDate) as updatedDate from audit_case_movement where user_role = 'INV' group by caseId) b "
			+ "where a.caseId = b.caseId and a.updatedDate = b.updatedDate and a.user_role = 'INV') b "
			+ "where a.caseId = b.caseId and a.caseStatus = 'Closed' and b.toId = :username", 
			nativeQuery = true)
	int getCaseClosedCount(@Param("username")String username);
	
	Case_movement save(Case_movement Case_lists);

}
