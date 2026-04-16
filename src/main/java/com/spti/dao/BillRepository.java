package com.spti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spti.entity.Bill;

import java.util.List;

@Repository

public interface BillRepository extends JpaRepository<Bill,Long>{
    List<Bill> findByStatus(String status);


}
