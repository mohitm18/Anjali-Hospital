package com.spti.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.spti.dao.BranchDao;
import com.spti.dao.PatientRepository;
import com.spti.dto.patient.PatientRequestDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.entity.Branch;
import com.spti.entity.Patient;
import com.spti.mapper.patient.PatientMapper;
import com.spti.service.PatientService;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientMapper patientMapper;

	@Override
	public Page<PatientResponseDto> getAllPatients( int branchId, Pageable pageable ) {
		Optional<Branch> opt = branchDao.findById( branchId );
		if ( opt.isPresent() ) {
			Page<Patient> entityPage = patientRepository.findAllByBranch( opt.get(), pageable );
			return new PageImpl<>( patientMapper.toList( entityPage.getContent() ), pageable, entityPage.getTotalElements() );
		}
		return null;
	}

	@Override
	public PatientResponseDto getPatientById( Long id ) {
		Optional<Patient> patientOpt = patientRepository.findById( id );
		if ( patientOpt.isPresent() ) {
			PatientResponseDto res = patientMapper.toDto( patientOpt.get() );
			return res;
		}
		return null;
	}

	@Override
	public boolean addPatient( PatientRequestDto patientRequestDto ) {
		try {
			Patient patient = patientMapper.toEntity( patientRequestDto );
			Optional<Branch> opt = branchDao.findById( patientRequestDto.getBranch() );
			if ( opt.isPresent() ) {
				patient.setBranch( opt.get() );
			}

			patientRepository.save( patient );
			return true;
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePatient( PatientRequestDto patientRequestDto ) {
		try {
			Patient patient = patientMapper.toEntity( patientRequestDto );
			Optional<Branch> opt = branchDao.findById( patientRequestDto.getBranch() );
			if ( opt.isPresent() ) {
				patient.setBranch( opt.get() );
			}

			patientRepository.save( patient );
			return true;
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deletePatient( Long id ) {
	}

	@Override
	public List<PatientResponseDto> findPatientsByPhoneNumber( int branchId, String phoneNumber ) {
		try {
			Optional<Branch> opt = branchDao.findById( branchId );
			if ( opt.isPresent() ) {
				List<Patient> patients = patientRepository.findByBranchAndPhoneNumber( opt.get(), phoneNumber );
				return patientMapper.toList( patients );
			}

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PatientResponseDto> findPatientsByPhoneNumber(String phoneNumber) {
		try {
	    List<Patient> list= patientRepository.findByPhoneNumber(phoneNumber);
	    if(list !=null) {
	         return	patientMapper.toList(list);
	    }
		}catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

    public Long getAllPatientsCounts(@PathVariable int branchId){
     return   patientRepository.countByBranchId(branchId);

    }
			
	}


