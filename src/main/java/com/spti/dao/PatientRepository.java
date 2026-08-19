package com.spti.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spti.entity.Branch;
import com.spti.entity.Patient;
import com.spti.entity.Staff;

public interface PatientRepository extends CrudRepository<Patient, Long> {

	Page<Patient> findAllByBranch( Branch branch, Pageable pageable );

	List<Patient> findByBranchAndPhoneNumber( Branch branch, String phoneNumber );

	List<Patient> findByPhoneNumber(String phoneNumber);

    Long countByBranchId(int branchId);

	Optional<Patient> findByEmail(String email);

    boolean existsByEmail(String email);

	@Query("SELECT COUNT(p) > 0 FROM Patient p WHERE p.phoneNumber = :phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
