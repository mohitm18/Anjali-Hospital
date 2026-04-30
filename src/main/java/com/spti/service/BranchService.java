package com.spti.service;

import java.util.List;

import com.spti.dto.BranchRequestDto;
import com.spti.dto.BranchResponceDto;
import com.spti.entity.Branch;

public interface BranchService {

	BranchRequestDto getById( int id );
	
	boolean add(BranchRequestDto branchDto);

	List<BranchResponceDto> all();
}
