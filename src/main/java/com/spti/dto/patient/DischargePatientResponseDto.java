package com.spti.dto.patient;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DischargePatientResponseDto {

	private Long dischargePatientId;
	private Long MLC_No;
	private Long IPD_No;
	private String consultatnt;
	private String diagnosis;
	private String clinical_History;
	private Long patientId;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dischargeDate;
    private  int bill;
	private Long amountPaid;
    private Long amounttobePaid;
    private  String admitDischargeStatus;
    private  String paymentStatus;
	private String paymentMode;
	private PatientResponseDto patientResponseDto;
	private AdmitPatientResponseDto admitPatientResponseDto;
	
}
