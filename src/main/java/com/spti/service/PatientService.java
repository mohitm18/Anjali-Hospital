package com.spti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spti.dto.patient.DischargePatientResponseDto;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.dto.patient.PatientRequestDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.dto.treatment.TreatmentResponse;

public interface PatientService {
	Page<PatientResponseDto> getAllPatients( int branchId, Pageable pageable );

	PatientResponseDto getPatientById( Long id );

	boolean addPatient( PatientRequestDto patientRequestDto );

	void deletePatient( Long id );

	boolean updatePatient( PatientRequestDto patientRequestDto );

	List<PatientResponseDto> findPatientsByPhoneNumber( int branchId, String phoneNumber );

	List<PatientResponseDto> findPatientsByPhoneNumber(String phoneNumber);

	Long getAllPatientsCounts();

	boolean isEmailExists(String email);  //frontEnd validation for email exists

	boolean isPhoneNumberExists(String phoneNumber);

    List<PatientOPDHistoryResponseDto> getOpdHistory(Long patientId);

    List<TreatmentResponse> getTreatmentDetails(Long patientId);

    List<DischargePatientResponseDto> getDischargeHistory(Long patientId);
   

}
