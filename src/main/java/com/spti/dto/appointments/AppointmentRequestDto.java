package com.spti.dto.appointments;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {
	private Long id;
	private Long patientId;
	private Long doctorId;
	@DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm" )
	private LocalDateTime appointmentDate;

	@DateTimeFormat( pattern = "HH:mm" )
	private LocalTime appointmentTime;
	private String status;
	private String diagnosis;
	private int branch;
	private String phoneNumber;
}
