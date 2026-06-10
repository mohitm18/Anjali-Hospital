package com.spti.dao;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spti.entity.Login;
import com.spti.entity.Staff;

public interface LoginDao extends CrudRepository<Login, Integer> {

	Login findByUsername(String username);

	@Modifying
	@Transactional
	@Query("UPDATE Login l SET l.role=:role WHERE l.username=:username")
	void updateStatus(@Param("role") String role,@Param("username") String username);

	Login findByPassword(String password);
	
	@Modifying
	@Transactional
	@Query("UPDATE Login l SET l.role=:role WHERE l.password=:password")
	void updateStatus1(@Param("role") String role,@Param("password") String password);

	Optional<Login> findByStaff(Staff staff);
}
