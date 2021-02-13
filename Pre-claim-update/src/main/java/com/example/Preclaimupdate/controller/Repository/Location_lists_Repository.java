package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Preclaimupdate.entity.Location_lists;

public interface Location_lists_Repository extends JpaRepository<Location_lists, Integer>{
	
	Location_lists findByLocationId(int locationId);

}
