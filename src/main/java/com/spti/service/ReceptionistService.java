package com.spti.service;

import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.dto.staff.StaffUpdateFieldsRequestDto;
import javax.validation.Valid;
import java.util.List;

public interface ReceptionistService {
    public boolean addReceptionist(@Valid StaffRequestDto dto);

    public boolean updateReceptionist(StaffRequestDto dto);

    public boolean deleteStaff(Long id);

    public StaffResponseDto getStaffById(Long id);

    public String updateField(Long id, StaffUpdateFieldsRequestDto dto);

    public StaffResponseDto getReceptionistByUsername(String username);

    List<StaffResponseDto> getAllStaff();

    boolean updateStaff(@Valid StaffRequestDto dto);

    //public List<StaffResponseDto> getStaffByBranch( int branchId );
}
