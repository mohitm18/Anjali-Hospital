package com.spti.controller;

import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import com.spti.dto.patientStatistics.PatientStatisticsResponseDto;
//import com.spti.dto.patient.BillRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spti.constants.MessageConstants;
import com.spti.dto.patient.PatientOPDHistoryRequestDTO;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.service.OpdPatientHistoryService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	}//new changes on 24 th feb

    @GetMapping("/patients/{patientId:\\d+}")
    public ResponseEntity<List<PatientOPDHistoryResponseDto>> patientOpdHistory(
            @PathVariable("patientId") Long patientId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(opdPatientHistoryService.getPatientOpdHistory(patientId));
    }

    @PostMapping("/history")
    public ResponseEntity<String> addOpdHistory(@Valid @RequestBody PatientOPDHistoryRequestDTO dto) {
        System.out.println("Bill = " + dto.getBill());
        System.out.println("PaidBill = " + dto.getPaidBill());
        System.out.println("BillStatus = " + dto.getBillStatus());
        System.out.println("PaymentType = " + dto.getPaymentType());
        System.out.println("PendingAmount = " + dto.getPendingAmount());
        boolean isAdded = opdPatientHistoryService.addOpdHistory(dto);
        if (isAdded)
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(MessageConstants.ADD_OPD_HISTORY_SUCCESS_MESSAGE);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(MessageConstants.ADD_OPD_HISTORY_ERROR_MSG);
    }

    @PostMapping("/updatePaidBill")
    public ResponseEntity<String> updatePaidBill(@RequestBody PatientOPDHistoryRequestDTO dto) {
        boolean isUpdated = opdPatientHistoryService.updatePaidBill(
                dto.getId(),
                dto.getBill() != null ? dto.getBill().doubleValue() : 0.0,
                dto.getPaidBill() != null ? dto.getPaidBill().doubleValue() : 0.0,
                dto.getPendingAmount() !=null ? dto.getPendingAmount().doubleValue() : 0.0,
                dto.getPaymentType());
        if (isUpdated) {
            return ResponseEntity.ok("Payment updated Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating bill");
        }
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

