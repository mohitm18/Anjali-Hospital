package com.spti.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "bills" )
public class Bill {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

//	@ManyToOne( fetch = FetchType.LAZY )
//	@JoinColumn( name = "admission_id", referencedColumnName = "admission_id" )
//	private PatientAdmission patientAdmission;

    @ManyToOne
    @JoinColumn( name = "patient_id" )
    //@NotEmpty(message = "patient cannot be empty")
    private Patient patient;

	private Integer amount;
	private Integer discount;
	private Integer finalBill;
	private Integer paidAmount;
	private Integer pendingAmount;
	private String status;

}
