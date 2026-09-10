package com.spti.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

@Setter
@Getter
@Entity
@Table( name = "appointments" )
public class Appointment {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "appointment_id" )
	private Long appointmentId;

	@ManyToOne
	@JoinColumn( name = "patient_id", nullable = false )
	private Patient patient;

	@ManyToOne
	@JoinColumn( name = "doctor_id", nullable = true )
	private Staff staff;
	
	@Column( name = "appointment_date", nullable = false )
	private LocalDateTime appointmentDate;

	@Column( name = "appointment_time", nullable = false )
	private LocalTime appointmentTime;

	private String status;

	@Column( columnDefinition = "TEXT" )
	private String diagnosis;

	@ManyToOne
	@JoinColumn( name = "branch" )
	private Branch branch;

	@Column( name = "phone_number" )
	private String phoneNumber;

}
