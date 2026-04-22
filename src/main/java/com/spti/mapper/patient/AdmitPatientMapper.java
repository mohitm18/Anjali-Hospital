package com.spti.mapper.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.spti.dto.patient.AdmitPatientRequestDto;
import com.spti.dto.patient.AdmitPatientResponseDto;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.entity.AdmitPatient;
import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;

@Component
public class AdmitPatientMapper {

    public AdmitPatient toEntity(AdmitPatientRequestDto dto) {
        AdmitPatient entity = new AdmitPatient();
        entity.setId(dto.getId());
        entity.setAdmitDischargeStatus(dto.getAdmitDischargeStatus());
        entity.setAdmissionDate(dto.getAdmissionDate());
        return entity;
    }

	public AdmitPatientResponseDto toDto(AdmitPatient admitPatient) {
		AdmitPatientResponseDto admitPatientResponseDto = new AdmitPatientResponseDto();
		admitPatientResponseDto.setId(admitPatient.getId());
		admitPatientResponseDto.setAdmitDischargeStatus(admitPatient.getAdmitDischargeStatus());
		admitPatientResponseDto.setAdmissionDate(admitPatient.getAdmissionDate());
		admitPatientResponseDto.setPatient(topatientResponesDto(admitPatient.getPatient()));

		return admitPatientResponseDto;
	}

	private PatientResponseDto topatientResponesDto(Patient patient) {
		PatientResponseDto patientResponseDto = new PatientResponseDto();
		patientResponseDto.setFirstName(patient.getFirstName());
		patientResponseDto.setLastName(patient.getLastName());
		patientResponseDto.setAddress(patient.getAddress());
		patientResponseDto.setAge(patient.getAge());
		patientResponseDto.setEmail(patient.getEmail());
		patientResponseDto.setGender(patient.getGender());
		patientResponseDto.setPhoneNumber(patient.getPhoneNumber());
		patientResponseDto.setId(patient.getId());
		patientResponseDto.setBranch(patient.getBranch().getId());

		return patientResponseDto;
	}

	public List<AdmitPatientResponseDto> toList(List<AdmitPatient> content) {
		List<AdmitPatientResponseDto> list = new ArrayList<>();
		for (AdmitPatient dto : content)
			list.add(toDto(dto));
		return list;
	}
		
		 public PatientResponseDto toPtientDto(Patient entity) { 
			  
			  PatientResponseDto patient = new PatientResponseDto();
			  patient.setId(entity.getId());
			  patient.setFirstName(entity.getFirstName());
			  patient.setLastName(entity.getLastName());
			  patient.setPhoneNumber(entity.getPhoneNumber());
			  patient.setAddress(entity.getAddress());
			  patient.setGender(entity.getGender());
			  patient.setAge(entity.getAge());
			  patient.setEmail(entity.getEmail());
			  
			  
			  return patient;
			  
			  }
		
		
		public AdmitPatientResponseDto toResponseDTO( AdmitPatient entity ) {
			AdmitPatientResponseDto dto = new AdmitPatientResponseDto();

         dto.setId(entity.getId());
         dto.setAdmissionDate(entity.getAdmissionDate());
         dto.setAdmitDischargeStatus(entity.getAdmitDischargeStatus());
         dto.setPatient(toPtientDto(entity.getPatient()));
         dto.setDischargedAt(entity.getDischargedAt());        
			return dto;
		}
		
		public List<AdmitPatientResponseDto> toResponseList(List<AdmitPatient> list) {
			List<AdmitPatientResponseDto> reslist = new ArrayList<>();
			for (AdmitPatient dto : list) {
				reslist.add(toResponseDTO(dto));
			}
			return reslist;
		}

	public List<PatientResponseDto> toPatientResponseDtoList(List<AdmitPatient> admitPatients) {
		return admitPatients.stream().map(this::toPatientResponseDto).collect(Collectors.toList());
	}

	public PatientResponseDto toPatientResponseDto(AdmitPatient admitPatient) {
		PatientResponseDto patientResponseDto = new PatientResponseDto();
		
		patientResponseDto.setFirstName(admitPatient.getPatient().getFirstName());
		patientResponseDto.setLastName(admitPatient.getPatient().getLastName());
		patientResponseDto.setPhoneNumber(admitPatient.getPatient().getPhoneNumber());
		patientResponseDto.setGender(admitPatient.getPatient().getGender());
		patientResponseDto.setAge(admitPatient.getPatient().getAge());
		patientResponseDto.setAddress(admitPatient.getPatient().getAddress());
		patientResponseDto.setEmail(admitPatient.getPatient().getEmail());
		patientResponseDto.setAdmitDischargeStatus(admitPatient.getAdmitDischargeStatus());
		
		return patientResponseDto;
	}

}
