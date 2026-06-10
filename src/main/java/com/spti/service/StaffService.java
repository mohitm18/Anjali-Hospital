package com.spti.service;

import java.util.List;

import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.entity.Role;

public interface StaffService {

    StaffResponseDto onboardStaff(StaffRequestDto request);

    List<Role> getAllRoles();

    List<StaffResponseDto> getAllStaff();

    StaffResponseDto getStaffById(Long id);

    void updateStaff(Long id, StaffRequestDto request);

    void deleteStaff(Long id);
}
