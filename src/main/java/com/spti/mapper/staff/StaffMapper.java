package com.spti.mapper.staff;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.spti.dto.staff.StaffUpdateFieldsRequestDto;
import org.springframework.stereotype.Component;
import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.entity.Staff;

@Component
public class StaffMapper {

	public Staff toEntity( @Valid StaffRequestDto staffDto ) {
		Staff entity = new Staff();

		entity.setId( staffDto.getId() );
		entity.setFirstName( staffDto.getFirstName() );
		entity.setLastName( staffDto.getLastName() );
		entity.setRole( staffDto.getRole() );
		entity.setUsername( staffDto.getUsername() );
		entity.setPhoneNumber( staffDto.getPhoneNumber() );
		entity.setAddress( staffDto.getAddress() );
		entity.setExperience( staffDto.getExperience() );
		entity.setStatus( staffDto.getStatus() );
		return entity;

	}

	public StaffResponseDto toDto( Staff staff ) {
		StaffResponseDto dto = new StaffResponseDto();
		dto.setId( staff.getId() );
		dto.setFirstName( staff.getFirstName() );
		dto.setLastName( staff.getLastName() );
		dto.setRole( staff.getRole() );
		dto.setUsername( staff.getUsername() );
		dto.setPhoneNumber( staff.getPhoneNumber() );
		dto.setAddress( staff.getAddress() );
		dto.setExperience( staff.getExperience() );
		dto.setStatus( staff.getStatus() );
        if ( staff.getBranch() != null ) {
            dto.setBranchId( staff.getBranch().getId() );
            dto.setBranchName( staff.getBranch().getName() );
        }
		return dto;
	}

	public List<StaffResponseDto> toList( List<Staff> staffs ) {
		List<StaffResponseDto> dtoList = new ArrayList<>();
		for ( Staff staff : staffs ) {
			dtoList.add( toDto( staff ) );
		}
		return dtoList;
	}

    public void updateFieldOfStaff(@Valid StaffUpdateFieldsRequestDto dto, Staff entity){

        if (dto.getId() != null){
            entity.setId(dto.getId());
        }
        if (dto.getFirstName() != null){
            entity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null){
            entity.setLastName(dto.getLastName());
        }
        if (dto.getRole() != null){
            entity.setRole(dto.getRole());
        }
        if (dto.getUsername() != null){
            entity.setUsername(dto.getUsername());
        }
        if (dto.getPhoneNumber() != null){
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getAddress() != null){
            entity.setAddress(dto.getAddress());
        }
        if (dto.getExperience() != null){
            entity.setExperience(dto.getExperience());
        }
    }

}
