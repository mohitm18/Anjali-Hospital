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
public class LoginRequestDto {
	private int id;
	private String username;
	private String password;
	private String role;
	private Staff staff;

}
