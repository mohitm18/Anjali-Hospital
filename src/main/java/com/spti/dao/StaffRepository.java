package com.spti.dao;

import com.spti.dto.staff.StaffResponseDto;
import com.spti.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// public interface StaffRepository extends CrudRepository<Staff, Long> {

// 	Staff findByEmail( String username );

// 	List<Staff> findByBranch( Branch branch );

// }

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("select count(s) > 0 from Staff s where s.phoneNumber=:phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    
    List<Staff> findByStatusAndRole_Name(String status, String name);
}

