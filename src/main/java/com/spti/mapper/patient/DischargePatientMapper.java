package com.spti.mapper.patient;

import com.spti.entity.DischargePatient;
import org.springframework.stereotype.Component;

import com.spti.dto.patient.DischargePatientRequestDto;

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

}