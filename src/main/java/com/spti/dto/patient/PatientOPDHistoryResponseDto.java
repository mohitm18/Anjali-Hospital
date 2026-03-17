package com.spti.dto.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.spti.entity.Diagnosis;
import com.spti.entity.Patient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientOPDHistoryResponseDto {

        private Long id;
        private String seenByDoctor;
        private DiagnosisResponseDto diagnosis;
        private String treatment;

        // Change to Double
        private Double bill;
        private Double pendingAmount;
        private Double paidBill; // Changed from float to Double

        private String billStatus;
        private PatientResponseDto patient;
        private int branch;
        private LocalDate treatmentDate;
        private String paymentType;

        // Financial summaries
        private Double totalBillOpd;
        private Double totalPendingBill;
        private Double totalPaidBill;

        private String note; // Fixed casing from 'Note' to 'note'

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime dateOfTreatment;
    }
