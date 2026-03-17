package com.spti.mapper.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spti.dto.patient.DiagnosisResponseDto;
import com.spti.dto.patient.PatientOPDHistoryRequestDTO;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.entity.Diagnosis;
import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;

@Component
public class PatientOPDHistoryMapper {

    public PatientOPDHistoryRequestDTO toRequestDTO(PatientOPDHistory entity) {
        if (entity == null) return null;
        PatientOPDHistoryRequestDTO dto = new PatientOPDHistoryRequestDTO();

        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatient() != null ? entity.getPatient().getId() : null);
        dto.setBill(entity.getBill());
        dto.setPaidBill(entity.getPaidBill()); // ADDED: Mapping paid_bill
        dto.setBillStatus(entity.getBillStatus());
        dto.setPendingAmount(entity.getPendingAmount());
        dto.setSeenByDoctor(entity.getSeenByDoctor());
        dto.setTreatment(entity.getTreatment());

        return dto;
    }

    public PatientResponseDto toPatientDto(Patient entity) {
        if (entity == null) return null;
        PatientResponseDto patient = new PatientResponseDto();
        patient.setId(entity.getId());
        patient.setFirstName(entity.getFirstName());
        patient.setLastName(entity.getLastName());
        patient.setPhoneNumber(entity.getPhoneNumber());
        patient.setAge(entity.getAge());
        return patient;
    }

    public DiagnosisResponseDto toDiagnosisDto(Diagnosis entity) {
        if (entity == null) return null;
        DiagnosisResponseDto diagnosis = new DiagnosisResponseDto();
        diagnosis.setId(entity.getId());
        diagnosis.setDiagnosis(entity.getDiagnosis());
        return diagnosis;
    }

    public PatientOPDHistoryResponseDto toResponseDTO(PatientOPDHistory entity) {
        if (entity == null) return null;
        PatientOPDHistoryResponseDto dto = new PatientOPDHistoryResponseDto();
        dto.setId(entity.getId());
        dto.setBill(entity.getBill());
        dto.setPaidBill(entity.getPaidBill()); // ADDED: Mapping paid_bill
        dto.setBillStatus(entity.getBillStatus());
        dto.setPendingAmount(entity.getPendingAmount());
        dto.setSeenByDoctor(entity.getSeenByDoctor());
        dto.setTreatment(entity.getTreatment());
        dto.setTreatmentDate(entity.getTreatmentDate());
        dto.setDateOfTreatment(entity.getDateOfTreatment());
        dto.setPaymentType(entity.getPaymentType());

        if (entity.getPatient() != null) {
            dto.setPatient(toPatientDto(entity.getPatient()));
        }
        if (entity.getDiagnosis() != null) {
            dto.setDiagnosis(toDiagnosisDto(entity.getDiagnosis()));
        }

        return dto;
    }

    public List<PatientOPDHistoryResponseDto> toResponseList(List<PatientOPDHistory> list) {
        List<PatientOPDHistoryResponseDto> reslist = new ArrayList<>();
        if (list != null) {
            for (PatientOPDHistory entity : list) {
                reslist.add(toResponseDTO(entity));
            }
        }
        return reslist;
    }

    public PatientOPDHistory toEntity(PatientOPDHistoryRequestDTO dto) {
        if (dto == null) return null;

        PatientOPDHistory history = new PatientOPDHistory();
        history.setId(dto.getId());
        history.setSeenByDoctor(dto.getSeenByDoctor());
        history.setTreatment(dto.getTreatment());
        history.setBill(dto.getBill());
        history.setPaidBill(dto.getPaidBill()); // ADDED: THIS SAVES TO DB
        history.setBillStatus(dto.getBillStatus());
        history.setPendingAmount(dto.getPendingAmount());

        // Use dates from DTO if available, otherwise set now
        history.setTreatmentDate(dto.getTreatmentDate() != null ? dto.getTreatmentDate() : LocalDate.now());
        history.setDateOfTreatment(dto.getDateOfTreatment() != null ? dto.getDateOfTreatment() : LocalDateTime.now());

        history.setPaymentType(dto.getPaymentType());
        history.setNote(dto.getNote());
        history.setDiagnosis(dto.getDiagnosis());

        return history;
    }

    public PatientOPDHistoryRequestDTO dtosetValue(PatientOPDHistoryRequestDTO dto,
                                                   PatientOPDHistoryResponseDto responseDto) {
        if (responseDto == null) return dto;

        dto.setSeenByDoctor(responseDto.getSeenByDoctor());
        dto.setTreatment(responseDto.getTreatment());
        dto.setBill(responseDto.getBill());
        dto.setPaidBill(responseDto.getPaidBill()); // ADDED: Syncing values
        dto.setPendingAmount(responseDto.getPendingAmount());
        dto.setTreatmentDate(responseDto.getTreatmentDate());
        dto.setDateOfTreatment(responseDto.getDateOfTreatment());

        if (responseDto.getPatient() != null) {
            dto.setPatientId(responseDto.getPatient().getId());
        }
        return dto;
    }

    public List<PatientOPDHistoryResponseDto> gettoResponseList(List<PatientOPDHistory> entityPage) {
        return toResponseList(entityPage);
    }
}