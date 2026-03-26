package com.spti.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.spti.dto.patientStatistics.PatientStatisticsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spti.dao.AdmitPatientRepository;
import com.spti.dao.PatientRepository;
import com.spti.dao.TreatmentRepository;
import com.spti.dto.patient.AdmitPatientRequestDto;
import com.spti.dto.patient.AdmitPatientResponseDto;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.dto.treatment.TreatmentRequest;
import com.spti.dto.treatment.TreatmentResponse;
import com.spti.entity.AdmitPatient;
import com.spti.entity.Branch;
import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;
import com.spti.entity.Treatment;
import com.spti.mapper.patient.AdmitPatientMapper;
import com.spti.mapper.patient.TreatmentMapper;
import com.spti.service.AdmitPatientService;

@Service
public class AdmitPatientServiceImpl implements AdmitPatientService {

	@Autowired
	AdmitPatientMapper admitPatientMapper;

	@Autowired
	AdmitPatientRepository admitPatientRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TreatmentMapper treatmentMapper;

	@Autowired
	private TreatmentRepository treatmentRepository;

	@Override  
	public boolean AdmitPatientAdd( AdmitPatientRequestDto dto) {

		try {
			AdmitPatient entity = admitPatientMapper.toEntity(dto);

			Optional<Patient> opt = patientRepository.findById(dto.getPatientId());
			if (opt.isPresent()) {
				entity.setPatient(opt.get());
				admitPatientRepository.save(entity);				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public AdmitPatientResponseDto getAdmitPatientBypatienId(Long id) {
		Optional<AdmitPatient> admitPatient = admitPatientRepository.findByPatient_idAndAdmitDischargeStatus(id,
				"Admit");

		if (admitPatient.isPresent())
			return admitPatientMapper.toDto(admitPatient.get());
		else
			return null;

	}

	@Override
	public Page<AdmitPatientResponseDto> allAdmitPatients(Pageable pageable) {

		Page<AdmitPatient> entityPage = admitPatientRepository.findByAdmitDischargeStatus("Admit", pageable);
		return new PageImpl<>(admitPatientMapper.toList(entityPage.getContent()), pageable,
				entityPage.getTotalElements());
	}

	@Override
	public boolean addAdmittedPatientTreatmentDetails(List<TreatmentRequest> treatmentRequestdto) {
		List<Treatment> treatmentList = new ArrayList<Treatment>();
		treatmentRequestdto.forEach(treatment -> {
			treatmentList.add(treatmentMapper.toEntity(treatment));
		});

		return treatmentRepository.saveAll(treatmentList) != null;
	}

	@Override
	public List<TreatmentResponse> getTreatmentDetailsByAdmittanceId(Long id) {
		// TODO Auto-generated method stub
		List<Treatment> treatmentList = treatmentRepository.findAllByAdmittanceId(id);
		List<TreatmentResponse> treatmentResponseList = new ArrayList<TreatmentResponse>();
		treatmentList.forEach(treatment -> {
			treatmentResponseList.add(treatmentMapper.toDto(treatment));
		});

		return treatmentResponseList;
	}


	@Override
	public List<PatientResponseDto> findByAdmissionDate(LocalDate admissionDate) {
		List<AdmitPatient> admitPatientsDates = admitPatientRepository.findByAdmissionDate(admissionDate);
		if (admitPatientsDates != null)
			return admitPatientMapper.toPatientResponseDtoList(admitPatientsDates);
		else
			return null;
	}

	@Override
	public List<PatientResponseDto> findByYear(int year) {
		List<AdmitPatient> admitPatientsByYear = admitPatientRepository.findByYear(year);
		if (admitPatientsByYear != null)
			return admitPatientMapper.toPatientResponseDtoList(admitPatientsByYear);
		else
			return null;
	}

	public List<PatientResponseDto> getPatientsBetweenDates(LocalDate startDate, LocalDate endDate) {
		List<AdmitPatient> admitPatientsRandom = admitPatientRepository.findByAdmitDateBetween(startDate, endDate);
		if (admitPatientsRandom != null)
			return admitPatientMapper.toPatientResponseDtoList(admitPatientsRandom);
		else
			return null;
	}
	@Override
	public List<AdmitPatientResponseDto> getTodayAdmitPatient(String todayrecord) {
		

		    if (todayrecord.equalsIgnoreCase("Today OpdPatient And Bill")) {
		    	LocalDate date = LocalDate.now();
		       
		    	List<AdmitPatient> entityPage = admitPatientRepository.findByAdmissionDateAndAdmitDischargeStatus(date, "Admit");
		    	return admitPatientMapper.toResponseList(entityPage);

		    } else if (todayrecord.equalsIgnoreCase("weeklyrecord")) {
		        LocalDate endDate =LocalDate.now();
		        LocalDate startDate = endDate.minusDays(7);
		        
		        List<AdmitPatient> entityPage = admitPatientRepository.findAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(startDate, endDate, "Admit");
		       
		       
		         return admitPatientMapper.toResponseList(entityPage);
		    } else {
		        LocalDate endDate = LocalDate.now();
		        LocalDate startDate = endDate.minusDays(30);
		        
		        List<AdmitPatient> entityPage = admitPatientRepository.findAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(startDate, endDate, "Admit");
		       
		        return admitPatientMapper.toResponseList(entityPage);
		    }
	}

	@Override
	public List<AdmitPatientResponseDto> getTodayDischargePatient(String todayrecord) {
		
		if (todayrecord.equalsIgnoreCase("Today OpdPatient And Bill")) {
			LocalDate date = LocalDate.now();
			List<AdmitPatient> entityPage = admitPatientRepository.findByDischargedDateAndAdmitDischargeStatus(date,"Discharge");
			return (admitPatientMapper.toResponseList(entityPage));			
		}
		else if (todayrecord.equalsIgnoreCase("weeklyrecord")) {
			
			LocalDate enddate = LocalDate.now();
			LocalDate startDate = enddate.minusDays(7);
			  
			List<AdmitPatient> entityPage = admitPatientRepository.findAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(startDate,enddate, "Discharge");
			
			return (admitPatientMapper.toResponseList(entityPage));
			
		}
		else {
			
		    LocalDate enddate = LocalDate.now();
		    LocalDate startDate = enddate.minusDays(30);
		  
		    List<AdmitPatient> entityPage = admitPatientRepository.findAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(startDate,enddate, "Discharge");
			
		    return (admitPatientMapper.toResponseList(entityPage));
		}

	}

	@Override
	public List<PatientResponseDto> findAllPatient() {
		List<AdmitPatient> patientResponseAllPateint= admitPatientRepository.findAllAdmit();
		if (patientResponseAllPateint != null)
			return admitPatientMapper.toPatientResponseDtoList(patientResponseAllPateint);
		else
			return null;
		
	}
	
	@Override
	public List<PatientResponseDto> getListOfPatient(String todayrecord) {
		if (todayrecord.equalsIgnoreCase("Today Patient")) {
			LocalDate date = LocalDate.now();
			List<AdmitPatient> entity = admitPatientRepository.findByPatientTodays(date);
			return admitPatientMapper.toPatientResponseDtoList(entity);
		} else if (todayrecord.equalsIgnoreCase("Weekly Patient")) {
			LocalDate enddate = LocalDate.now().plusDays(1);
			LocalDate startDate = enddate.minusDays(7);
			List<AdmitPatient> entityPage = admitPatientRepository.findByAdmitDateBetween(startDate, enddate);
			return admitPatientMapper.toPatientResponseDtoList(entityPage);
		} else if (todayrecord.equalsIgnoreCase("Monthly Patient")) {
			LocalDate enddate = LocalDate.now().plusDays(1);
			LocalDate startDate = enddate.minusDays(31);
			List<AdmitPatient> entityPage = admitPatientRepository.findByAdmitDateBetween(startDate, enddate);
			return admitPatientMapper.toPatientResponseDtoList(entityPage);
		} else if (todayrecord.equalsIgnoreCase("all patient")) {
	        List<AdmitPatient> entityPage = admitPatientRepository.findAllAdmit();
	        return admitPatientMapper.toPatientResponseDtoList(entityPage);
	    } else {
			
			return Collections.emptyList();
		}
	}

	@Override
	public List<AdmitPatientResponseDto> getTodayWeeklyMonthlyDischargePatient(String todayrecord) {
	    if (todayrecord.equalsIgnoreCase("Today Patient")) {
	        LocalDate date = LocalDate.now();
	        List<AdmitPatient> entityPage = admitPatientRepository.findGetByAdmissionDateAndAdmitDischargeStatus(date, "Discharge");
	        return admitPatientMapper.toResponseList(entityPage);
	    } else if (todayrecord.equalsIgnoreCase("weekly Patient")) {
	        LocalDate endDate = LocalDate.now().plusDays(1);
	        LocalDate startDate = endDate.minusDays(7);
	        List<AdmitPatient> entityPage = admitPatientRepository.findGetAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(startDate, endDate, "Discharge");
	        return admitPatientMapper.toResponseList(entityPage);
	    } else if (todayrecord.equalsIgnoreCase("all patient")) {
	        List<AdmitPatient> entityPage = admitPatientRepository.findAllDischargePatients();
	        return admitPatientMapper.toResponseList(entityPage);
	    } else {
	        LocalDate endDate = LocalDate.now().plusDays(1);
	        LocalDate startDate = endDate.minusDays(30);
	        List<AdmitPatient> entityPage = admitPatientRepository.findGetAdmitPatientListBetweenAdmissionDateAndAdmitDischargeStatus(startDate, endDate, "Discharge");
	        return admitPatientMapper.toResponseList(entityPage);
	    }
	}

  @Override
public List<PatientStatisticsResponseDto> getMonthlyAdmitStats(){

      List<AdmitPatient>  admitHistory =  admitPatientRepository.findAll();

      int currentYear = java.time.LocalDate.now().getYear();

      Map<Integer, Long> monthlyCounts = admitHistory.stream()
              .filter(h -> h.getAdmissionDate() != null)
              .filter(h -> h.getAdmissionDate().getYear() == currentYear)
              .collect(Collectors.groupingBy(
                      h -> h.getAdmissionDate().getMonthValue(),
                      java.util.stream.Collectors.counting()
              ));

      List<PatientStatisticsResponseDto> responseList = new java.util.ArrayList<>();

      for (int i = 1; i <= 12; i++) {
          String monthName = java.time.Month.of(i).getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);
          Long count = monthlyCounts.getOrDefault(i, 0L);

          responseList.add(new PatientStatisticsResponseDto(monthName, count));
      }

      return responseList;
  }

 @Override
  public  List<PatientStatisticsResponseDto> getMonthlyDischargeStats(){
     List<AdmitPatient>  dischargeHistory =  admitPatientRepository.findAllDischargePatients();
     int currentYear = java.time.LocalDate.now().getYear();

     Map<Integer, Long> monthlyCounts = dischargeHistory.stream()
             .filter(h -> h.getDischargedAt() != null)
             .filter(h -> h.getDischargedAt().getYear() == currentYear)
             .collect(Collectors.groupingBy(
                     h -> h.getDischargedAt().getMonthValue(),
                     java.util.stream.Collectors.counting()
             ));

     List<PatientStatisticsResponseDto> responseList = new java.util.ArrayList<>();

     for (int i = 1; i <= 12; i++) {
         String monthName = java.time.Month.of(i).getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);
         Long count = monthlyCounts.getOrDefault(i, 0L);

         responseList.add(new PatientStatisticsResponseDto(monthName, count));
     }

     return responseList;
 }
//	@Override
//	public List<AdmitPatientResponseDto> getPatientsBetweenStartToEndDates(String todayrecord) {
//		LocalDate endDate = LocalDate.now().plusDays(1);
//        LocalDate startDate = endDate.minusDays(30);
//        List<AdmitPatient> entityPage = admitPatientRepository.findByStartAndEndDate(startDate ,endDate);
//        
//        
//		return admitPatientMapper.toResponseList(entityPage);
//	}

}
