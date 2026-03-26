package com.spti.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.spti.entity.Branch;
import com.spti.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

	Page<Patient> findAllByBranch( Branch branch, Pageable pageable );

	List<Patient> findByBranchAndPhoneNumber( Branch branch, String phoneNumber );

	List<Patient> findByPhoneNumber(String phoneNumber);

    Long countByBranchId(int branchId);

}
