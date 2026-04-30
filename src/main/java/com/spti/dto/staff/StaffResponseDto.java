package com.spti.dto.staff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffResponseDto {
	private Long id;
	private String firstName;
	private String lastName;
    private String username;
    private String phoneNumber;
    private String status;
    private String experience;
    private String address;
	private String role;
    private int branchId;
    private String branchName;
}
