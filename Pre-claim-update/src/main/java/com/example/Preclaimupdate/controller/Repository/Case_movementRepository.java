package com.example.Preclaimupdate.controller.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Case_lists;
import com.example.Preclaimupdate.entity.Case_movement;

@Repository
public interface Case_movementRepository extends JpaRepository<Case_movement, Integer>{
	
	Case_movement findByCaseId(int CaseId);
	
	@Query(value="select * from Case_movement where from_id =6060;", nativeQuery = true)
	List<Case_movement> getCaselists1(@Param("username")String username);
	
	@Query(value="select * from Case_movement where to_id =6060;", nativeQuery = true)
	List<Case_movement> getCaselists(@Param("username")String username);
	
	Case_movement save(Case_movement Case_lists);

}
