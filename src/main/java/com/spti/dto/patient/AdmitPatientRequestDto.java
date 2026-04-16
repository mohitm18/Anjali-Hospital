package com.spti.dto.patient;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdmitPatientRequestDto {
    private long id;
    private String admitDischargeStatus;
    private Long patientId;
    private LocalDateTime admissionDate;
}
