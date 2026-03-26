package com.spti.dto.patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.spti.entity.Diagnosis;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientOPDHistoryRequestDTO {

        private Long id;
        private String seenByDoctor;
        private Diagnosis diagnosis;
        private String treatment;
        private Double bill;
        private Double paidBill; // Added this field
        private Double pendingAmount;
        private String billStatus;
        private Long patientId;
        private String paymentType;
        private String note;
        private LocalDate treatmentDate;
        private LocalDateTime dateOfTreatment;
    }




