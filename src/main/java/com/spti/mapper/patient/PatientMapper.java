package com.spti.mapper.patient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spti.dto.patient.PatientRequestDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.entity.Patient;

@Component
public class PatientMapper {

	public Patient toEntity( PatientRequestDto requestDto ) {
		Patient entity = new Patient();

		entity.setId( requestDto.getId() );
		entity.setFirstName( requestDto.getFirstName() );
		entity.setLastName( requestDto.getLastName() );
		entity.setGender( requestDto.getGender() );
		entity.setAddress( requestDto.getAddress() );
		entity.setAge( requestDto.getAge() );
		entity.setPhoneNumber( requestDto.getPhoneNumber() );
		entity.setEmergencyContact(requestDto.getEmergencyContact());
		entity.setEmail( requestDto.getEmail() );
		entity.setBloodGroup(requestDto.getBloodGroup());
		entity.setHeight(requestDto.getHeight());
		entity.setWeight(requestDto.getWeight());
		entity.setAllergies(requestDto.getAllergies());
		entity.setMedicalHistory(requestDto.getMedicalHistory());
		entity.setMaritalStatus(requestDto.getMaritalStatus());
		entity.setOccupation(requestDto.getOccupation());
		entity.setCity(requestDto.getCity());
		entity.setState(requestDto.getState());
		entity.setPincode(requestDto.getPincode());
		return entity;
	}

	public PatientResponseDto toDto( Patient entity ) {

		PatientResponseDto dto = new PatientResponseDto();

		dto.setId( entity.getId() );
		dto.setFirstName( entity.getFirstName() );
		dto.setLastName( entity.getLastName() );
		dto.setGender( entity.getGender() );
		dto.setAge(entity.getAge());
		dto.setAddress( entity.getAddress() );
		dto.setPhoneNumber( entity.getPhoneNumber() );
		dto.setEmail( entity.getEmail());
		dto.setEmergencyContact(entity.getEmergencyContact());
		dto.setBloodGroup(entity.getBloodGroup());
		dto.setHeight(entity.getHeight()!=null?entity.getHeight():0);
		dto.setWeight(entity.getWeight()!=null?entity.getWeight():0);
		dto.setAllergies(entity.getAllergies());	
		dto.setMedicalHistory(entity.getMedicalHistory());
		dto.setMaritalStatus(entity.getMaritalStatus());
		dto.setOccupation(entity.getOccupation());
		dto.setCity(entity.getCity());
		dto.setState(entity.getState());
		dto.setPincode(entity.getPincode());
		
		return dto;
	}

	public List<PatientResponseDto> toList( List<Patient> content ) {
		List<PatientResponseDto> list = new ArrayList<>();
		for ( Patient dto : content )
			list.add( toDto( dto ) );
		return list;
	}
}
