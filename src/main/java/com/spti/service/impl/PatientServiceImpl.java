package com.spti.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spti.dao.AdmitPatientRepository;
import com.spti.dao.BranchDao;
import com.spti.dao.DischargePatientRepository;
import com.spti.dao.LoginDao;
import com.spti.dao.PatientOPDHistoryRepository;
import com.spti.dao.PatientRepository;
import com.spti.dao.TreatmentRepository;
import com.spti.dto.patient.DischargePatientResponseDto;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.dto.patient.PatientRequestDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.dto.treatment.TreatmentResponse;
import com.spti.entity.AdmitPatient;
import com.spti.entity.Branch;
import com.spti.entity.DischargePatient;
import com.spti.entity.Login;
import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;
import com.spti.entity.Treatment;
import com.spti.mapper.patient.PatientMapper;
import com.spti.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientMapper patientMapper;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
    private LoginDao loginDao;

	@Autowired
	private PatientOPDHistoryRepository patientOPDHistoryRepository;

	@Autowired
	private TreatmentRepository treatmentRepository;

	@Autowired
	private AdmitPatientRepository admitPatientRepository;
	
	@Autowired
	private DischargePatientRepository dischargePatientRepository;

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

	// @Override
	// public boolean addPatient( PatientRequestDto patientRequestDto ) {
	// 	try {
	// 		System.out.println( patientRequestDto);
	// 		Patient patient = patientMapper.toEntity( patientRequestDto );
	// 		Optional<Branch> opt = branchDao.findById( patientRequestDto.getBranch() );
	// 		if ( opt.isPresent() ) {
	// 			patient.setBranch( opt.get() );
	// 		}

	// 		patientRepository.save( patient );
	// 		return true;
	// 	} catch ( Exception e ) {
	// 		e.printStackTrace();
	// 	}
	// 	return false;
	// }

	@Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addPatient(PatientRequestDto patientRequestDto) {
        try {
        
            Patient patient = patientMapper.toEntity(patientRequestDto);

			if (patientRequestDto.getBranch() > 0) {
				Optional<Branch> opt = branchDao.findById((int) patientRequestDto.getBranch());
				opt.ifPresent(patient::setBranch);
			}

            Patient savedPatient = patientRepository.save(patient);

            Login login = new Login();
            login.setUsername(savedPatient.getEmail());
            
            String rawPassword = (patientRequestDto.getPassword() != null && !patientRequestDto.getPassword().isEmpty())
                    ? patientRequestDto.getPassword()
                    : "Patient@123"; 
            
            login.setPassword(passwordEncoder.encode(rawPassword));
            login.setRole("PATIENT");
            login.setPatient(savedPatient); 
            login.setStaff(null);           

            loginDao.save(login);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Patient registration failed: " + e.getMessage());
        }
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

    @Override
public Long getAllPatientsCounts() {
    return patientRepository.count();
}

//already exists new functionality of email and phoneNumber
@Override
public boolean isEmailExists(String email) {
    try {
        return patientRepository.existsByEmail(email);
    } catch (DataAccessException e) {
        throw new RuntimeException("Unable to check email existence", e);
    }
}

@Override
public boolean isPhoneNumberExists(String phoneNumber) {
    try {
        return patientRepository.existsByPhoneNumber(phoneNumber);
    } catch (DataAccessException e) {
        throw new RuntimeException("Unable to check phone number existence", e);
    }
}

@Override
public List<PatientOPDHistoryResponseDto> getOpdHistory(Long patientId) {
	List<PatientOPDHistory> historyList = patientOPDHistoryRepository
			.findByPatient_IdOrderByDateOfTreatmentDesc(patientId);
	List<PatientOPDHistoryResponseDto> responseList = new java.util.ArrayList<>();
	for (PatientOPDHistory history : historyList) {
		PatientOPDHistoryResponseDto dto = new PatientOPDHistoryResponseDto();
		dto.setId(history.getId());
		dto.setSeenByDoctor(history.getSeenByDoctor());
		dto.setTreatment(history.getTreatment());
		dto.setBill(history.getBill());
		dto.setPaidBill(history.getPaidBill());
		dto.setPendingAmount(history.getPendingAmount());
		dto.setBillStatus(history.getBillStatus());
		dto.setTreatmentDate(history.getTreatmentDate());
		dto.setPaymentType(history.getPaymentType());
		dto.setNote(history.getNote());
		dto.setDateOfTreatment(history.getDateOfTreatment());
		/*
		 * * PatientResponseDto आणि DiagnosisResponseDto * mapping तुझ्या existing
		 * mapper प्रमाणे कर.
		 */ responseList.add(dto);
	}
	return responseList;
}

@Override
public List<TreatmentResponse> getTreatmentDetails(Long patientId) {
	List<AdmitPatient> admissions = admitPatientRepository.findByPatient_IdOrderByAdmissionDateDesc(patientId);
	List<TreatmentResponse> responseList = new java.util.ArrayList<>();
	for (AdmitPatient admission : admissions) {
		List<Treatment> treatments = treatmentRepository.findAllByAdmittanceId(admission.getId());
		for (Treatment treatment : treatments) {
			TreatmentResponse dto = new TreatmentResponse();
			dto.setMedicineName(treatment.getMedicineName());
			dto.setMedicineTime(treatment.getMedicineTime());
			dto.setTreatmentDate(treatment.getTreatmentDate());
			dto.setBill(treatment.getBill());
			dto.setBillPaid(treatment.getBillPaid());
			dto.setPaymentMode(treatment.getPaymentMode());
			dto.setPaidAmount(treatment.getPaidAmount());
			dto.setAmounttobePaid(treatment.getAmounttobePaid());
			responseList.add(dto);
		}
	}
	return responseList;
}

@Override
public List<DischargePatientResponseDto> getDischargeHistory(Long patientId) {
	List<DischargePatient> dischargeList = dischargePatientRepository.findByPatient_IdOrderByIdDesc(patientId);
	List<DischargePatientResponseDto> responseList = new java.util.ArrayList<>();
	for (DischargePatient discharge : dischargeList) {
		DischargePatientResponseDto dto = new DischargePatientResponseDto();
		dto.setDischargePatientId(discharge.getId());
		dto.setMLC_No(discharge.getMLC_No());
		dto.setIPD_No(discharge.getIPD_No());
		dto.setConsultatnt(discharge.getConsultatnt());
		dto.setDiagnosis(discharge.getDiagnosis());
		dto.setClinical_History(discharge.getClinical_History());
		dto.setPatientId(patientId);
		dto.setBill(discharge.getBill());
		dto.setAmountPaid(discharge.getAmountPaid());
		dto.setAmounttobePaid(discharge.getAmounttobePaid());
		dto.setAdmitDischargeStatus(discharge.getAdmitDischargeStatus());
		dto.setPaymentStatus(discharge.getPaymentStatus());
		responseList.add(dto);
	}
	return responseList;
}

}

