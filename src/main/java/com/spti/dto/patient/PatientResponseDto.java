package com.spti.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String address;
	private int age;
	private String phoneNumber;
	private String emergencyContact;
	private String bloodGroup;
	private int height;
	private int weight;
	private String allergies;
	private String medicalHistory;
	private String maritalStatus;
	private String occupation;
	private String city;
	private String state;
	private String pincode;
	private String email;
	private int branch;
	private String admitDischargeStatus;
}
