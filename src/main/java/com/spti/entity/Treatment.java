package com.spti.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="treatment_details")
public class Treatment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="id")
	private int id;
	
	@Column(name="admittance_id")
	private long admittanceId;
	
	@Column(name="medicine_name")
	private String medicineName;
	
	@Column(name="medicine_time")
	private String medicineTime;
	
	@Column(name="treatment_date")
	private  LocalDate treatmentDate;
	
	@Column(name="bill")
	private  int bill;
	
	@Column(name="bill_paid")
	private  String billPaid;
	
	@Column(name="payment_mode")
	private String paymentMode;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

    @Positive(message = "Amount paid must be a positive number")
    @Column(name = "amountPaid")
    private Long paidAmount;

    @Column(name = "amounttobePaid")
    private Long amounttobePaid;

}
