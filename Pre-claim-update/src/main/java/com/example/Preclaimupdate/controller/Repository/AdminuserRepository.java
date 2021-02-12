package com.example.Preclaimupdate.controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Preclaimupdate.entity.Admin_user;

@Repository
public interface AdminuserRepository extends JpaRepository<Admin_user, Integer> {
	
	Admin_user findByUsername(String username);

}