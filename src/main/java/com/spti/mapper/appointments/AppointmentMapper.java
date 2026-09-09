package com.spti.mapper.appointments;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spti.dto.appointments.AppointmentRequestDto;
import com.spti.dto.appointments.AppointmentResponseDto;
import com.spti.entity.Appointment;

@Component
public class AppointmentMapper {

	public Appointment toEntity( AppointmentRequestDto requestDto ) {
		Appointment entity = new Appointment();
		entity.setAppointmentId( requestDto.getId() );
		entity.setAppointmentDate( requestDto.getAppointmentDate() );
		entity.setAppointmentTime( requestDto.getAppointmentTime() );
		//entity.setStaff( requestDto.getDoctorId() );
		entity.setStatus( requestDto.getStatus() );
		entity.setDiagnosis( requestDto.getDiagnosis() );
		entity.setPhoneNumber( requestDto.getPhoneNumber() );
		return entity;
	}

	public AppointmentResponseDto toDto(Appointment entity) {

    AppointmentResponseDto dto = new AppointmentResponseDto();

    dto.setId(entity.getAppointmentId());

    // Patient ID + Patient Name
    if (entity.getPatient() != null) {
        dto.setPatientId(entity.getPatient().getId());

        dto.setPatientName(
            entity.getPatient().getFirstName() + " "
            + entity.getPatient().getLastName()
        );
    }

    // Doctor ID
    if (entity.getStaff() != null) {
        dto.setDoctorId(entity.getStaff().getId());
    }

    dto.setAppointmentDate(entity.getAppointmentDate());
    dto.setAppointmentTime(entity.getAppointmentTime());
    dto.setStatus(entity.getStatus());
    dto.setDiagnosis(entity.getDiagnosis());

    if (entity.getBranch() != null) {
        dto.setBranch(entity.getBranch().getId());
    }

    dto.setPhoneNumber(entity.getPhoneNumber());

    return dto;
}
	// public AppointmentResponseDto toDto( Appointment entity ) {
	// 	AppointmentResponseDto dto = new AppointmentResponseDto();
	// 	dto.setId( entity.getAppointmentId() );
	// 	dto.setPatientId( entity.getPatient().getId() );
	// 	//dto.setDoctorId( entity.getStaff().getId() );
	// 	dto.setAppointmentDate( entity.getAppointmentDate() );
	// 	dto.setAppointmentTime( entity.getAppointmentTime() );
	// 	dto.setStatus( entity.getStatus() );
	// 	dto.setNotes( entity.getNotes() );
	// 	dto.setBranch( entity.getBranch().getId() );
	// 	dto.setPhoneNumber( entity.getPhoneNumber() );
	// 	return dto;
	// }

	public List<AppointmentResponseDto> toList( List<Appointment> list ) {
		return list.stream().map( this::toDto ).collect( Collectors.toList() );
	}
}
