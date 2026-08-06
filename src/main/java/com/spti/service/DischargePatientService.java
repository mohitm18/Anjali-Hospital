package com.spti.service;

import java.util.List;

import com.spti.dto.patient.DischargePatientRequestDto;
import com.spti.dto.patient.DischargePatientResponseDto;

public interface DischargePatientService {

	boolean dischargePatienAdd(DischargePatientRequestDto dto);

	 List<DischargePatientResponseDto> getAllDischargePatientsData();

}
