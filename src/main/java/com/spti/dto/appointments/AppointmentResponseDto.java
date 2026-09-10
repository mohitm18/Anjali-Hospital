package com.spti.dto.appointments;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {
	private Long id;
	private Long patientId;
	private Long doctorId;
	private LocalDateTime appointmentDate;
	private LocalTime appointmentTime;
	private String status;
	private String diagnosis;
	private int branch;
	private String phoneNumber;
	private String patientName;
	private String doctorName;
}
