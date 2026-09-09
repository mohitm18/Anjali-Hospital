package com.spti.service;

import java.util.List;

import javax.validation.Valid;

import com.spti.dto.appointments.AppointmentRequestDto;
import com.spti.dto.appointments.AppointmentResponseDto;

public interface AppointmentService {

	boolean addAppointment( AppointmentRequestDto dto );

	List<AppointmentResponseDto> getTodaysAppointments( int branchId );

	AppointmentResponseDto getAppointmentById( Long id );

	boolean updateAppointment( @Valid AppointmentRequestDto dto );

	List<AppointmentResponseDto> getTodaysAppointmentsForADoctor( Long doctorId, int branchId );

    boolean updateAppointmentStatus(Long id, String status);

    List<AppointmentResponseDto> getFilteredAppointmentsForDoctor(
        String filter,
        Long doctorId,
        int branchId);

}
 