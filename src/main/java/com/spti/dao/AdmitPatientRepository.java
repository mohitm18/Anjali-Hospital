package com.spti.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;    
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.entity.AdmitPatient;
import com.spti.entity.PatientOPDHistory;
import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;

@Repository
public interface AdmitPatientRepository extends JpaRepository<AdmitPatient, Long> {

	Optional<AdmitPatient> findByPatient_id(Long id);

	Page<AdmitPatient> findByAdmitDischargeStatus(String string, Pageable pageable);

	Optional<AdmitPatient> findByPatient_idAndAdmitDischargeStatus(Long id, String string);

	@Query(value = "SELECT * FROM hospital.admit_patient WHERE DATE(admit_date) = :admissionDate", nativeQuery = true)
	List<AdmitPatient> findByAdmissionDate(@Param("admissionDate") LocalDate admissionDate);

	@Query(value = "SELECT * FROM hospital.admit_patient WHERE YEAR(admit_date) = :year", nativeQuery = true)
	List<AdmitPatient> findByYear(@Param("year") int year);

	@Query(value = "SELECT * FROM hospital.admit_patient WHERE admit_and_discharge_status = 'Admit' AND admit_date >= :startDate AND admit_date < :endDate", nativeQuery = true)
	List<AdmitPatient> findByAdmitDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query(value = "SELECT * FROM hospital.admit_patient WHERE admit_and_discharge_status = 'Admit' AND DATE(admit_date) = :date", nativeQuery = true)
	List<AdmitPatient> findByPatientTodays(@Param("date") LocalDate date);

    // Discharge Query
    
    @Query(name = "findByAdmissionDateAndAdmitDischargeStatus", value =  "SELECT a FROM AdmitPatient a WHERE DATE(a.admissionDate) = DATE(:date) AND a.admitDischargeStatus = :status")
    List<AdmitPatient> findByAdmissionDateAndAdmitDischargeStatus(@Param("date") LocalDate date, @Param("status") String status);


    @Query(name = "findByAdmissionDateAndAdmitDischargeStatus", value =  "SELECT a FROM AdmitPatient a WHERE DATE(a.dischargedAt) = DATE(:date) AND a.admitDischargeStatus = :status")
    List<AdmitPatient> findByDischargedDateAndAdmitDischargeStatus(@Param("date") LocalDate date, @Param("status") String status);

    @Query(name = "findAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus", value = "SELECT p FROM AdmitPatient p WHERE DATE(p.admissionDate) >= DATE(:startDate) AND DATE(p.admissionDate) <= DATE(:endDate) AND p.admitDischargeStatus = :status")
     List<AdmitPatient> findAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") String status);

    @Query(value = "SELECT * FROM hospital.admit_patient WHERE discharged_at BETWEEN :startDate AND :endDate AND admit_and_discharge_status = :status", nativeQuery = true)
    List<AdmitPatient> findGetAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("status") String status);

    @Query(value = "SELECT * FROM hospital.admit_patient WHERE DATE(discharged_at) = :date AND admit_and_discharge_status = :status", nativeQuery = true)
    List<AdmitPatient> findGetByAdmissionDateAndAdmitDischargeStatus(@Param("date") LocalDate date, @Param("status") String status);
    
    @Query(value = "SELECT * FROM hospital.admit_patient WHERE admit_and_discharge_status = 'Discharge'", nativeQuery = true)
    List<AdmitPatient> findAllDischargePatients();
   
    @Query(value = "SELECT * FROM hospital.admit_patient WHERE admit_and_discharge_status = 'Admit'", nativeQuery = true)
	List<AdmitPatient> findAllAdmit();

	//List<AdmitPatient> findByStartAndEndDate(LocalDate startDate, LocalDate endDate);
}

//@Query(value = "SELECT * FROM hospital.admit_patient WHERE admit_date >= :startDate AND admit_date < :endDatePlusOneDay", nativeQuery = true)
//List<AdmitPatient> findByAdmitDateBetween(@Param("startDate") LocalDate startDate, @Param("endDatePlusOneDay") LocalDate endDatePlusOneDay);	

//@Query( value = "SELECT * FROM hospital.admit_patient WHERE admit_date =:date", nativeQuery = true)
//List<AdmitPatient> findByPatientTodays(@Param("date") LocalDate date);
	
//	@Query(value = "SELECT * FROM hospital.admit_patient WHERE admit_date BETWEEN :startDate AND :endDate", nativeQuery = true)
//	List<AdmitPatient> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//   
//	@Query(value = "SELECT * FROM hospital.admit_patient WHERE WEEK(admit_date, 1) = :week", nativeQuery = true)
//    List<AdmitPatient> findByAdmitDateYearAndAdmitDateWeek( @Param("week") int week);

//    SELECT * FROM hospital.admit_patient;
//
//    SELECT * FROM hospital.admit_patient WHERE DATE(admit_date) = '2024-04-15';
//
//    SELECT * FROM hospital.admit_patient where year(admit_date) = '2023';

