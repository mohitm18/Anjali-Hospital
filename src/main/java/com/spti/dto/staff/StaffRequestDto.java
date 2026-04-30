package com.spti.dto.staff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffRequestDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String role;
	private String username;
	private String phoneNumber;
	private String address;
	private String experience;
	private String status;
	private int branch;
}
