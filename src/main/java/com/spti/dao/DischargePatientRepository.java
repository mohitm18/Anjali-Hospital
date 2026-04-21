package com.spti.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spti.entity.AdmitPatient;
import com.spti.entity.DischargePatient;

@Repository
public interface DischargePatientRepository extends CrudRepository<DischargePatient, Long> {

//	Optional<DischargePatient> findByPatient_idAndAdmitDischargeStatus(Long patientId, String string);


    //List<DischargePatient> findByStatus(String status);


}
