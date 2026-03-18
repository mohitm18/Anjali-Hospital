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

        @NotNull(message = "ID is required")
        private Long id;

        @NotBlank(message = "Seen by Doctor is required")
        @Size(max = 255, message = "Seen by Doctor cannot be longer than 255 characters")
        private String seenByDoctor;

        @NotBlank(message = "Diagnosis is required")
        @Size(max = 500, message = "Diagnosis cannot be longer than 500 characters")
        private Diagnosis diagnosis;

        @NotBlank(message = "Treatment is required")
        @Size(max = 1000, message = "Treatment cannot be longer than 1000 characters")
        private String treatment;

        // Change these to Double for calculations
        @NotBlank(message = "Bill is required")
        private Double bill;

        @NotBlank(message = "paidBill is required")
        private Double paidBill; // Added this field

        @NotBlank(message = "Pending Amount is required")
        private Double pendingAmount;

        @NotBlank(message = "Bill Status is required")
        private String billStatus;

        @NotNull(message = "Patient ID is required")
        private Long patientId;

        @NotBlank(message = "Payment Type is required")
        private String paymentType;

        @Size(max = 2000, message = "Note cannot be longer than 2000 characters")
        private String note;

        @NotNull(message = "Treatment Date is required")
        private LocalDate treatmentDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime dateOfTreatment;
    }




