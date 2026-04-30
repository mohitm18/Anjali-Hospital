package com.spti.dto;

import com.spti.dto.staff.StaffResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponceDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String role;
    private String status;
    private String experience;
    private String address;
    private int branchId;
    private String branchName;
    private Long loginId;
    private StaffResponseDto staffResponseDto;


}
