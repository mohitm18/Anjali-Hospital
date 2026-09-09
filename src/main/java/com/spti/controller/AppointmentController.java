package com.spti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spti.constants.MessageConstants;
import com.spti.dto.appointments.AppointmentRequestDto;
import com.spti.dto.appointments.AppointmentResponseDto;
import com.spti.service.AppointmentService;

@RestController
@RequestMapping( "/appointments" )
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<String> addAppointment( @Valid @RequestBody AppointmentRequestDto dto ) {
		boolean isAdded = appointmentService.addAppointment( dto );
		if ( isAdded )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.ADD_APPOINMENT_SUCCESS_MESSAGE );
		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.ADD_APPOINMENT_ERROR_MESSAGE );

	}

	@GetMapping( "/todays/branch/{branchId}" )
	public ResponseEntity<List<AppointmentResponseDto>> todaysAppointment( @PathVariable int branchId ) {
		return ResponseEntity.status( HttpStatus.OK ).body( appointmentService.getTodaysAppointments( branchId ) );
	}

	@GetMapping( "/id/{id}" )
	public ResponseEntity<AppointmentResponseDto> getAppointmentById( @PathVariable Long id ) {
		return ResponseEntity.status( HttpStatus.OK ).body( appointmentService.getAppointmentById( id ) );
	}

	@PutMapping
	public ResponseEntity<String> updateAppointment( @Valid @RequestBody AppointmentRequestDto dto ) {
		boolean isUpdated = appointmentService.updateAppointment( dto );
		if ( isUpdated )
			return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.UPDATE_APPOINMENT_SUCCESS_MESSAGE );
		else
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.UPDATE_APPOINMENT_ERROR_MESSAGE );

	}

	@GetMapping( "/todays/doctors/{doctorId}/branch/{branchId}" )
	public ResponseEntity<List<AppointmentResponseDto>> todaysAppointmentForDoctor( @PathVariable Long doctorId, @PathVariable int branchId ) {
		return ResponseEntity.status( HttpStatus.OK ).body( appointmentService.getTodaysAppointmentsForADoctor( doctorId, branchId ) );
	}


	@PostMapping("/status/{id}")
    public ResponseEntity<String> updateAppointmentStatus(
        @PathVariable Long id,
        @RequestParam String status) {

    boolean updated =appointmentService.updateAppointmentStatus(id, status);

    if (updated) {
        return ResponseEntity.ok("Appointment status updated successfully");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to update appointment status");
}

@GetMapping("/filter")
public ResponseEntity<List<AppointmentResponseDto>> filterAppointments(
        @RequestParam String filter,
        @RequestParam Long doctorId,
        @RequestParam int branchId) {

    return ResponseEntity.ok(appointmentService.getFilteredAppointmentsForDoctor(filter,doctorId,branchId));
}
}
