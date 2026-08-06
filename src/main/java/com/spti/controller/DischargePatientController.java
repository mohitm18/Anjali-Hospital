package com.spti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spti.constants.MessageConstants;
import com.spti.dto.patient.DischargePatientRequestDto;
import com.spti.dto.patient.DischargePatientResponseDto;
import com.spti.service.DischargePatientService;

@RestController
@RequestMapping("/discharge")
public class DischargePatientController {
	
	@Autowired
	private DischargePatientService dischargePatientService;
	
	@PostMapping
	public ResponseEntity<String> dischargePatienAdd( @Valid @RequestBody DischargePatientRequestDto dto ) {
		boolean isAdded = dischargePatientService.dischargePatienAdd( dto );
		if ( isAdded )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.ADD_ADMISSION_SUCCESS_MESSAGE );

		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.ADD_ADMISSION_ERROR_MESSAGE );
		
	}

	@GetMapping("/getAllDischargePatientsData")
	public List<DischargePatientResponseDto> getAllDischargePatientsData(){
		
		System.out.println("Inside RestControler getAllDischargePatientsData method");

	            return dischargePatientService.getAllDischargePatientsData();

	}

}
