package com.spti.mapper.patient;

import org.springframework.stereotype.Component;

import com.spti.dto.patient.BillRequestDto;
import com.spti.dto.patient.BillResponseDto;
import com.spti.entity.Bill;


@Component
public class BillMapper {

    public Bill toEntity(BillRequestDto dto) {

        Bill bill = new Bill();
        bill.setFinalBill(dto.getFinalBill());
        bill.setPaidAmount(dto.getPaidAmount());
        bill.setPendingAmount(dto.getPendingAmount());
        bill.setStatus(dto.getStatus());
        return bill;
    }

    public BillResponseDto toResponseDto(Bill bill) {
        BillResponseDto dto = new BillResponseDto();
        dto.setId(bill.getId());
        dto.setPatientId(bill.getPatient().getId());
        String fullName =
                bill.getPatient().getFirstName() + " " +
                        bill.getPatient().getLastName();
        dto.setPatientName(fullName);

        dto.setFinalBill(bill.getFinalBill());
        dto.setPaidAmount(bill.getPaidAmount());
        dto.setPendingAmount(bill.getPendingAmount());
        dto.setStatus(bill.getStatus());

        return dto;
    }

}
