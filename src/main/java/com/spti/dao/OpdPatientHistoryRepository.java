package com.spti.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;

@Repository
public interface OpdPatientHistoryRepository extends CrudRepository<PatientOPDHistory, Long> {

	List<PatientOPDHistory> findAllByPatient(Patient patient);

	List<PatientOPDHistory> findByPatientOrderByIdDesc(Patient patient);

	@Query(name = "findPatientOPDHistoryListByTreatmentDate", value = "SELECT p FROM PatientOPDHistory p WHERE p.treatmentDate = :date")
	List<PatientOPDHistory> findPatientOPDHistoryListByTreatmentDate(@Param("date") LocalDate date);

	@Query(name = "findPatientOPDHistoryPageByTreatmentDate", value = "SELECT p FROM PatientOPDHistory p WHERE p.treatmentDate = :date")
	Page<PatientOPDHistory> findPatientOPDHistoryPageByTreatmentDate(@Param("date") LocalDate date, Pageable pageable);
	
	@Query(name = "findPatientOPDHistoryPageBetweenTreatmentDates", value = "SELECT p FROM PatientOPDHistory p WHERE p.treatmentDate BETWEEN :startDate AND :endDate")
	Page<PatientOPDHistory> findPatientOPDHistoryPageBetweenTreatmentDates(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate, Pageable pageable);

	@Query(name = "findPatientOPDHistoryListBetweenTreatmentDates", value = "SELECT p FROM PatientOPDHistory p WHERE p.treatmentDate BETWEEN :startDate AND :endDate")
	List<PatientOPDHistory> findPatientOPDHistoryListBetweenTreatmentDates(@Param("startDate") LocalDate startDate,	@Param("endDate") LocalDate endDate);

	List<PatientOPDHistory>findByTreatmentDate(LocalDate date);

	List<PatientOPDHistory> findPatientOPDHistoryListByTreatmentDateAndPaymentType(LocalDate date, String paymentType);

	List<PatientOPDHistory> findByTreatmentDateBetweenAndPaymentType(LocalDate startDate, LocalDate enddate,String paymentType);

	@Query("SELECT poh FROM PatientOPDHistory poh JOIN poh.diagnosis d WHERE poh.treatmentDate = :date AND d.diagnosis = :disease AND ((:ages = '10to18' AND poh.patient.age BETWEEN 10 AND 18) OR (:ages = 'above18' AND poh.patient.age > 18))")
    List<PatientOPDHistory> findByTreatmentDateAndDiagnosisAndAge(@Param("date") LocalDate date, @Param("disease") String disease, @Param("ages") String ages);

	@Query("SELECT poh FROM PatientOPDHistory poh JOIN poh.diagnosis d WHERE poh.treatmentDate BETWEEN :startDate AND :endDate AND d.diagnosis = :disease AND ((:ages = '10to18' AND poh.patient.age BETWEEN 10 AND 18) OR (:ages = 'above18' AND poh.patient.age > 18))")
    List<PatientOPDHistory> findByTreatmentDateBetweenAndDiagnosisAndAge(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("disease") String disease, @Param("ages") String ages);



}
