package com.spti.dto;

import com.spti.entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponceDto {
	private int id;
	private String name;
	private String username;
	private String role;
	private String status;
	private int branchId;
	private String branchName;
	private Long loginId;
	private Staff staff;

}
