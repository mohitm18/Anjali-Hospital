package com.spti.dao;

import com.spti.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// public interface StaffRepository extends CrudRepository<Staff, Long> {

// 	Staff findByEmail( String username );

// 	List<Staff> findByBranch( Branch branch );

// }

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmail(String email);
    boolean existsByEmail(String email);
}
