package com.spti.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.spti.dto.patientStatistics.PatientStatisticsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spti.constants.MessageConstants;
import com.spti.dto.patient.PatientOPDHistoryRequestDTO;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.service.OpdPatientHistoryService;

@RestController
@RequestMapping( "/opds" )
public class OpdPatientController {

	@Autowired
	private OpdPatientHistoryService opdPatientHistoryService;
	
	
	@GetMapping("/todayOpdPatientHistory/{disease}/{todayrecord}/{ages}")
	public List<PatientOPDHistoryResponseDto> getOpdPatientHistory(@PathVariable String disease,@PathVariable String todayrecord, @PathVariable String ages) {

		List<PatientOPDHistoryResponseDto> opdPatientHistorys = opdPatientHistoryService.opdPatientHistory(disease,todayrecord, ages);
		if (opdPatientHistorys == null || opdPatientHistorys.isEmpty()) {
			return Collections.emptyList();
		} else {
			return opdPatientHistorys;
		}
	}

	@GetMapping( "/patients/{patientId}" )
	public ResponseEntity<List<PatientOPDHistoryResponseDto>> patientOpdHistory( @PathVariable Long patientId ) {
		return ResponseEntity.status( HttpStatus.OK ).body( opdPatientHistoryService.getPatientOpdHistory( patientId ) );
	}

	@PostMapping( "/history" )
	public ResponseEntity<String> addOpdHistory( @Valid @RequestBody PatientOPDHistoryRequestDTO dto ) {

		boolean isAdded = opdPatientHistoryService.addOpdHistory( dto );
		if ( isAdded )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.ADD_OPD_HISTORY_SUCCESS_MESSAGE );

		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.ADD_OPD_HISTORY_ERROR_MSG );
	}
	
	
	@PostMapping( "/updatePaidBill" )
	public ResponseEntity<String> updatePaidBill( @RequestBody PatientOPDHistoryRequestDTO dto ) {

		boolean isAdded = opdPatientHistoryService.updatePaidBill( dto );
		if ( isAdded )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.ADD_OPD_HISTORY_SUCCESS_MESSAGE );

		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.ADD_OPD_HISTORY_ERROR_MSG );
	}

	
	@GetMapping( "/opdPatienBill/{todayrecord}" )
	public PatientOPDHistoryResponseDto opdPatienBill(@PathVariable String todayrecord ) {
	
		return  opdPatientHistoryService.opdPatienBill(todayrecord ) ;
	}
	
	@GetMapping( "/opdPatienBillOnlineDashbord/{todayrecord}" )
	public PatientOPDHistoryResponseDto opdPatienBillOnlineDashbord(@PathVariable String todayrecord ) {
	
		return  opdPatientHistoryService.opdPatienBillOnlineDashbord(todayrecord ) ;
	}
	
	@GetMapping( "/opdPatienBillCashDashbord/{todayrecord}" )
	public PatientOPDHistoryResponseDto opdPatienBillCashDashbord(@PathVariable String todayrecord ) {
	
		return  opdPatientHistoryService.opdPatienBillCashDashbord(todayrecord ) ;
	}
	
	@GetMapping( "/todayOpdPatientDashbord/{todayrecord}" )
	public List<PatientOPDHistoryResponseDto> GetTodayOpdPatient(@PathVariable String todayrecord  ) {
		
		return  opdPatientHistoryService.GetTodayOpdPatient(todayrecord);
	}
	
	@GetMapping( "/patientsOpd/{id}" )
	public PatientOPDHistoryResponseDto getOpdRecordByid(@PathVariable Long id ) {
	
		return  opdPatientHistoryService.getOpdRecordByid(id ) ;
	}
	
//	@GetMapping( "/opdPatienTotalBill" )
//	public ResponseEntity<Object> opdPatienTotalBill( ) {
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( opdPatientHistoryService.opdPatienTotalBill( ) );
//	}

    @GetMapping("/getMonthlyOPDStats")
    public List<PatientStatisticsResponseDto>getMonthlyOPDStats(){
      return  opdPatientHistoryService.getMonthlyOPDStats();

    }


}

