package com.spti.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.spti.entity.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long> {

    Optional<Staff> findByUsername(String username);
    List<Staff> findAll();
//    List<Staff> findByBranch(Branch branch);
//    List<Staff> findAll(int branchId) ;
//    //Staff findByEmail( String username );
//    Optional<Staff> findById(Long id);

}
