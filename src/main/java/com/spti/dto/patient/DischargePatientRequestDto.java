package com.spti.dto.patient;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DischargePatientRequestDto {

	private Long dischargePatientId;

	@NotNull(message = "MLC Number cannot be null")
	@Min(value = 1, message = "MLC Number must be a positive number")
	private Long MLC_No;

	@NotNull(message = "IPD Number cannot be null")
	@Min(value = 1, message = "IPD Number must be a positive number")
	private Long IPD_No;

    @NotNull(message = "consultant Number cannot be null")
	private String consultatnt;

	@NotBlank(message = "Diagnosis cannot be blank")
	private String diagnosis;

	private String clinical_History;

	private Long patientId;

	@NotBlank(message = "Admit Discharge Status cannot be blank")
	private String admitDischargeStatus;

    private  int bill;
	@NotNull(message = "Amount paid cannot be null")
	@Min(value = 0, message = "Amount paid must be zero or a positive number")
	private Long amountPaid;

	private Long amounttobePaid;
    private  String paymentStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dischargedAt;
}
