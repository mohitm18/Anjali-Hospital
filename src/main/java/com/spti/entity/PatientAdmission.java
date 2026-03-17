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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "patient_admissions" )
public class PatientAdmission {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "admission_id" )
	private Long admissionId;

	@ManyToOne
	@JoinColumn( name = "patient_id", nullable = false )
	private Patient patient;

//	@ManyToOne
//	@JoinColumn( name = "ward_id", nullable = false )
//	private Ward ward;

//	@ManyToOne
//	@JoinColumn( name = "cot_id", nullable = false )
//	private Cot cot;

	@Column( name = "admission_date", nullable = false )
	private LocalDateTime admissionDate;

	@Column( name = "discharge_date" )
	private LocalDateTime dischargeDate;

//	@ManyToOne
//	@JoinColumn( name = "doctor_id", nullable = false )
//	private Doctor doctor;

	@Column( name = "next_of_kin_name" )
	private String nextOfKinName;

	@Column( name = "next_of_kin_relationship" )
	private String nextOfKinRelationship;

	@Column( name = "next_of_kin_phone_number" )
	private String nextOfKinPhoneNumber;

	@ManyToOne
	@JoinColumn( name = "branch", nullable = false )
	private Branch branch;

	@Column( name = "admission_status" )
	private String admissionStatus;
}
