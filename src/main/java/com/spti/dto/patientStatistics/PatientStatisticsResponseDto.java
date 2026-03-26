package com.spti.dto.patientStatistics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientStatisticsResponseDto {
    private String month;
    private Long count;
}
