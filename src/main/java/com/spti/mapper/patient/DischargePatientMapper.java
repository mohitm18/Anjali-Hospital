package com.spti.mapper.patient;

import org.springframework.stereotype.Component;

import com.spti.dto.patient.DischargePatientRequestDto;
import com.spti.dto.patient.DischargePatientResponseDto;
import com.spti.entity.DischargePatient;

@Component
public class DischargePatientMapper {

    public DischargePatient toEntity(DischargePatientRequestDto dto) {

        DischargePatient entity = new DischargePatient();

        entity.setMLC_No(dto.getMLC_No());
        entity.setIPD_No(dto.getIPD_No());
        entity.setConsultatnt(dto.getConsultatnt());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setClinical_History(dto.getClinical_History());
        entity.setAdmitDischargeStatus(dto.getAdmitDischargeStatus());
        entity.setBill(dto.getBill());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setAmounttobePaid(dto.getAmounttobePaid());

        return entity;
    }

public DischargePatientResponseDto toResponseDto(DischargePatient entity) {

        DischargePatientResponseDto dto = new DischargePatientResponseDto();

        dto.setMLC_No(entity.getMLC_No());
        dto.setIPD_No(entity.getIPD_No());
        dto.setConsultatnt(entity.getConsultatnt());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setClinical_History(entity.getClinical_History());
        dto.setAdmitDischargeStatus(entity.getAdmitDischargeStatus());
        dto.setBill(entity.getBill());
        dto.setPaymentStatus(entity.getPaymentStatus());
        dto.setAmountPaid(entity.getAmountPaid());
        dto.setAmounttobePaid(entity.getAmounttobePaid());
       
        return dto;
    }


}