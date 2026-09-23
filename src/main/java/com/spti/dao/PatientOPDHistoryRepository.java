package com.spti.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spti.entity.PatientOPDHistory;

@Repository
public interface PatientOPDHistoryRepository
        extends CrudRepository<PatientOPDHistory, Long> {

    List<PatientOPDHistory> findByPatient_IdOrderByDateOfTreatmentDesc(
            Long patientId
    );
}

