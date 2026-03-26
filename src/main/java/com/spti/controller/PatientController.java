package com.spti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spti.constants.MessageConstants;
import com.spti.dto.patient.PatientRequestDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.service.PatientService;

@RestController
@RequestMapping( "/patients" )
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping( "/branch/{branchId}" )
	public ResponseEntity<Page<PatientResponseDto>> getAllPatients( @PathVariable int branchId, @RequestParam int pageNo, Pageable pageable ) {
		pageable = PageRequest.of( pageNo, 50 );
		return ResponseEntity.status( HttpStatus.OK ).body( patientService.getAllPatients( branchId, pageable ) );
	}
	
	@GetMapping("/phone/{phoneNumber}")
	public ResponseEntity<List<PatientResponseDto>> getPatientByPhone(@PathVariable String phoneNumber ){
		return ResponseEntity.status(HttpStatus.OK).body(patientService.findPatientsByPhoneNumber(phoneNumber));
		
	}

	@GetMapping( "/{id}" )
	public PatientResponseDto getPatientById( @PathVariable Long id ) {
		return patientService.getPatientById( id );
	}

	@PostMapping
	public ResponseEntity<String> createPatient( @Valid @RequestBody PatientRequestDto patientRequestDto ) {
		boolean isAdded = patientService.addPatient( patientRequestDto );
		if ( isAdded )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.ADD_PATIENT_SUCCESS_MESSAGE );

		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.ADD_PATIENT_ERROR_MSG );

	}

	@PutMapping
	public ResponseEntity<String> updatePatient( @Valid @RequestBody PatientRequestDto patientRequestDto ) {
		boolean isAdded = patientService.updatePatient( patientRequestDto );
		if ( isAdded )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.UPDATE_PATIENT_SUCCESS_MESSAGE );

		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.UPDATE_PATIENT_ERROR_MESSAGE );

	}

	@DeleteMapping( "/{id}" )
	public void deletePatient( @PathVariable Long id ) {
		patientService.deletePatient( id );
	}

	@GetMapping( "/branch/{branchId}/phoneNumber/{phoneNumber}" )
	public ResponseEntity<List<PatientResponseDto>> findPatientsByPhoneNumber( @PathVariable int branchId, @PathVariable String phoneNumber ) {

		return ResponseEntity.status( HttpStatus.OK ).body( patientService.findPatientsByPhoneNumber( branchId, phoneNumber ) );

	       }

           @GetMapping("/getAllPatientsCounts/{branchId}")
           public Long getAllPatientsCounts(@PathVariable Integer branchId){

          return patientService.getAllPatientsCounts(branchId);

           }


    }
