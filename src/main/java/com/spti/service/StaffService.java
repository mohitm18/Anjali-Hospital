package com.spti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spti.dto.staff.ChangePasswordDto;
import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.entity.Role;

public interface StaffService {

    StaffResponseDto onboardStaff(StaffRequestDto request);

    List<Role> getAllRoles();

    Page<StaffResponseDto> getAllStaff(Pageable pageable);

    StaffResponseDto getStaffById(Long id);

    void updateStaff(Long id, StaffRequestDto request);

    void deleteStaff(Long id);

    boolean isEmailExists(String email);

    boolean isPhoneNoExists(String phoneNumber);

    List<StaffResponseDto> getActiveDoctors();
    
    void changePassword(Long id, ChangePasswordDto dto);
}
