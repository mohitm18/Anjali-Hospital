package com.spti.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table( name = "admit_patient" )
public class AdmitPatient {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "Admit_And_Discharge_Status" )
	//@NotEmpty(message = "Name cannot be empty")
   // @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
	private String admitDischargeStatus;
	
	@ManyToOne
	@JoinColumn( name = "patient_id" )
	//@NotEmpty(message = "patient cannot be empty")
	private Patient patient;
	
	@Column( name = "admit_Date" )
	//@NotNull
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime admissionDate;

	@Column( name = "mLC_No" )
	//@NotNull(message = "MLC number cannot be null")
   // @PositiveOrZero(message = "MLC number must be a positive or zero value")
	private Long MLCNo;

	@Column( name = "IPD_No" )
	//@NotNull(message = "IPD number cannot be null")
   // @PositiveOrZero(message = "IPD number must be a positive or zero value")
	private Long IPDNo;

	
	@Column( name = "consultatnt" )
   //@NotBlank(message = "Consultant cannot be blank")
	private String consultatnt;

	@Column( name = "diagnosis" )
	//@NotBlank(message = "Diagnosis cannot be blank")
	private String diagnosis;

	@Column( name = "clinical_History" )
	//@NotBlank(message = "Clinical history cannot be blank")
	private String clinicalHistory;

	@Column( name = "amount_paid" )
	//@NotNull(message = "Amount paid cannot be null")
   // @PositiveOrZero(message = "Amount paid must be a positive or zero value")
	private Long amountPaid;

	@Column( name = "amount_tobepaid" )
	//@NotNull(message = "Amount to be paid cannot be null")
   // @PositiveOrZero(message = "Amount to be paid must be a positive or zero value")
	private Long amounttobePaid;

	@Column(name="discharged_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dischargedAt;
	
}
