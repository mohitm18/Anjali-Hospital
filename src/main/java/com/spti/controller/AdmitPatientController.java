package com.spti.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import com.spti.dto.patientStatistics.PatientStatisticsResponseDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spti.constants.MessageConstants;
import com.spti.dto.patient.AdmitPatientRequestDto;
import com.spti.dto.patient.AdmitPatientResponseDto;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.dto.treatment.TreatmentRequest;
import com.spti.dto.treatment.TreatmentResponse;
import com.spti.entity.AdmitPatient;
import com.spti.service.AdmitPatientService;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admits")
public class AdmitPatientController {

	@Autowired
	private AdmitPatientService admitPatientService;

	@PostMapping
	public ResponseEntity<String> AdmitPatientAdd( @Valid @RequestBody AdmitPatientRequestDto dto) {
		boolean isAdded = admitPatientService.AdmitPatientAdd(dto);
		if (isAdded)
			return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.ADD_ADMISSION_SUCCESS_MESSAGE);

		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstants.ADD_ADMISSION_ERROR_MESSAGE);

	}

	@GetMapping("/patient/id/{id}")
	public ResponseEntity<AdmitPatientResponseDto> getAdmitPatientBypatienId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(admitPatientService.getAdmitPatientBypatienId(id));
	}

	@GetMapping
	public ResponseEntity<Page<AdmitPatientResponseDto>> allAdmitPatients(@RequestBody @RequestParam int pageNo,
			Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(admitPatientService.allAdmitPatients(PageRequest.of(pageNo, 50)));
	}

	@PostMapping("/treatment/{id}")
	public ResponseEntity<String> addAdmittedPatientTreatmentDetails(
			@RequestBody List<TreatmentRequest> treatmentRequestdto, @PathVariable Long id) {
		AdmitPatientResponseDto admitPatientResponseDto = admitPatientService.getAdmitPatientBypatienId(id);
		treatmentRequestdto.forEach(treatment -> {
			treatment.setAdmittanceId(admitPatientResponseDto.getId());
		});

		boolean isTreatmentDetailsAdded = admitPatientService.addAdmittedPatientTreatmentDetails(treatmentRequestdto);
		if (isTreatmentDetailsAdded)
			return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.ADD_Treatment_SUCCESS_MESSAGE);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstants.ADD_Treatment_ERROR_MESSAGE);

	}

	@GetMapping("/treatment/{id}")
	public ResponseEntity<List<TreatmentResponse>> getTreatmentDetails(@PathVariable Long id) {
		AdmitPatientResponseDto admitPatientResponseDto = admitPatientService.getAdmitPatientBypatienId(id);
		List<TreatmentResponse> treatmentResponseList = admitPatientService
				.getTreatmentDetailsByAdmittanceId(admitPatientResponseDto.getId());

		return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseList);

	}

	@GetMapping("/getByTodaysPatient/{admissionDate}")
	public ResponseEntity<List<PatientResponseDto>> getPatientByTodays(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate admissionDate) {
		List<PatientResponseDto> admitPatientByTodays = admitPatientService.findByAdmissionDate(admissionDate);
		if (admitPatientByTodays != null) {
			return ResponseEntity.status(HttpStatus.OK).body(admitPatientByTodays);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(admitPatientByTodays);
		}
	}

	@GetMapping("/getByYear/{year}")
	public ResponseEntity<List<PatientResponseDto>> getPatientByYear(@PathVariable int year) {
		List<PatientResponseDto> admitPatientByYear = admitPatientService.findByYear(year);
		if (admitPatientByYear != null)
			return ResponseEntity.status(HttpStatus.OK).body(admitPatientByYear);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(admitPatientByYear);

	}

	@GetMapping("/admit-patients/{startDate}/{endDate}")
	public ResponseEntity<List<PatientResponseDto>> getAdmitPatientsRandomDates(
			@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		List<PatientResponseDto> admitPatientRandomDates = admitPatientService.getPatientsBetweenDates(startDate,
				endDate);
		if (admitPatientRandomDates != null)
			return ResponseEntity.status(HttpStatus.OK).body(admitPatientRandomDates);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(admitPatientRandomDates);
	}

	@GetMapping("/getAllPatient")
	public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
		List<PatientResponseDto> patientResponseAllPatients = admitPatientService.findAllPatient();
		if (patientResponseAllPatients != null)
			return ResponseEntity.status(HttpStatus.OK).body(patientResponseAllPatients);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(patientResponseAllPatients);
	}
	
	@GetMapping("/todaysPatient/{date}")
	public ResponseEntity<?> getListOfPatients(@PathVariable("date") String date) {
	    List<PatientResponseDto> listOfPatients = admitPatientService.getListOfPatient(date);
	    if (listOfPatients.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listOfPatients);
	    }
	    return ResponseEntity.ok(listOfPatients);
	}

	@GetMapping( "/todayAdmitPatientDashbord/{todayrecord}" )
	public List<AdmitPatientResponseDto> getTodayAdmitPatient(@PathVariable String todayrecord  ) {
		
		return  admitPatientService.getTodayAdmitPatient(todayrecord);
	}
	  
	@GetMapping( "/disacharge/todayDischargePatientDashbord/{todayrecord}" )
	public List<AdmitPatientResponseDto> getTodayDischargePatient(@PathVariable String todayrecord  ) {
		System.out.println("Work");  
		return  admitPatientService. getTodayDischargePatient(todayrecord);
		
	}
	
	@GetMapping( "/disacharge/todayWeeklyMonthlyDischargePatient/{todayrecord}" )
	public List<AdmitPatientResponseDto> getTodayWeeklyMonthyDischargePatient(@PathVariable String todayrecord  ) {  
		return  admitPatientService. getTodayWeeklyMonthlyDischargePatient(todayrecord);
		
	}

    @GetMapping("/getMonthlyAdmitStats")
    public List<PatientStatisticsResponseDto> getMonthlyAdmitStats(){
       // System.out.println("Hello Utkarsh");
        return admitPatientService.getMonthlyAdmitStats();
    }

    @GetMapping("/getMonthlyDischargeStats")
    public List<PatientStatisticsResponseDto> getMonthlyDischargeStat(){

        return admitPatientService.getMonthlyDischargeStats();
    }
//	
//	@GetMapping("/getdataWithStartAndEndDate/{todayrecord}")
//	public ResponseEntity<List<AdmitPatientResponseDto>> getdataWithStartAndToDates(@PathVariable String todayrecord) {
//		List<AdmitPatientResponseDto> admitPatientRandomDates = admitPatientService.getPatientsBetweenStartToEndDates(todayrecord);
//		if (admitPatientRandomDates != null)
//			return ResponseEntity.status(HttpStatus.OK).body(admitPatientRandomDates);
//		else
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(admitPatientRandomDates);
//	}

}
