package com.spti.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spti.entity.Treatment;

public interface TreatmentRepository extends CrudRepository<Treatment, Integer> {

	List<Treatment> findAllByAdmittanceId(Long id);

	Treatment findByAdmittanceId(Long id);

}
