package com.spti.billingUtility;

import java.util.List;
import org.springframework.stereotype.Component;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;

@Component
public class BillingUtility {

    public PatientOPDHistoryResponseDto totalBillOfOPDPatient(List<PatientOPDHistoryResponseDto> dtoList) {

        double totalBillOpd = 0.0;
        double totalPaidBill = 0.0;
        double totalPendingBill = 0.0;

        for (PatientOPDHistoryResponseDto dto : dtoList) {
            // Null safety: default to 0.0 if values are null
            double bill = (dto.getBill() != null) ? dto.getBill() : 0.0;
            double pending = (dto.getPendingAmount() != null) ? dto.getPendingAmount() : 0.0;
            double paid = (dto.getPaidBill() != null) ? dto.getPaidBill() : 0.0;

            totalBillOpd += bill;
            totalPaidBill += paid;
            totalPendingBill += pending;
        }

        PatientOPDHistoryResponseDto summaryDto = new PatientOPDHistoryResponseDto();
        summaryDto.setTotalBillOpd(totalBillOpd);
        summaryDto.setTotalPaidBill(totalPaidBill);
        summaryDto.setTotalPendingBill(totalPendingBill);

        return summaryDto;
    }

    public List<PatientOPDHistoryResponseDto> getPaidBill(List<PatientOPDHistoryResponseDto> dto) {
        for(PatientOPDHistoryResponseDto patientDto : dto) {
            calculatePaidAmount(patientDto);
        }
        return dto;
    }

    public PatientOPDHistoryResponseDto getPaidBillSingleOpd(PatientOPDHistoryResponseDto dto) {
        return calculatePaidAmount(dto);
    }

    // Helper method to reduce code duplication
    private PatientOPDHistoryResponseDto calculatePaidAmount(PatientOPDHistoryResponseDto dto) {
        double bill = (dto.getBill() != null) ? dto.getBill() : 0.0;
        double pending = (dto.getPendingAmount() != null) ? dto.getPendingAmount() : 0.0;

        // Logic: Paid = Total - Pending
        dto.setPaidBill(bill - pending);
        return dto;
    }
}