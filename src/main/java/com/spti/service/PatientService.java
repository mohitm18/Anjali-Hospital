package com.spti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spti.dto.patient.PatientRequestDto;
import com.spti.dto.patient.PatientResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface PatientService {
	Page<PatientResponseDto> getAllPatients( int branchId, Pageable pageable );

	PatientResponseDto getPatientById( Long id );

	boolean addPatient( PatientRequestDto patientRequestDto );

	void deletePatient( Long id );

	boolean updatePatient( PatientRequestDto patientRequestDto );

	List<PatientResponseDto> findPatientsByPhoneNumber( int branchId, String phoneNumber );

	List<PatientResponseDto> findPatientsByPhoneNumber(String phoneNumber);

    Long getAllPatientsCounts(@PathVariable int branchId);

}
