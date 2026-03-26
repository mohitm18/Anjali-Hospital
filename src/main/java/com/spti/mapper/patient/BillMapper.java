//package com.spti.mapper.patient;
//
//import org.springframework.stereotype.Component;
//
//import com.spti.dto.patient.BillRequestDto;
//import com.spti.dto.patient.BillResponseDto;
//import com.spti.entity.Bill;
//
//@Component
//public class BillMapper {
//
//    public Bill toEntity(BillRequestDto dto) {
//
//        if (dto == null) return null;
//
//        Bill bill = new Bill();
//
//        bill.setId(dto.getId());
//
//        bill.setAmount(dto.getAmount());
//        bill.setDiscount(dto.getDiscount());
//        bill.setFinalBill(dto.getFinalBill());
//        bill.setPaidAmount(dto.getPaidAmount());
//        bill.setPendingAmount(dto.getPendingAmount());
//        bill.setStatus(dto.getStatus());
//
//        return bill;
//    }
//
//    public BillResponseDto toDto(Bill entity) {
//
//        if (entity == null) return null;
//
//        BillResponseDto dto = new BillResponseDto();
//
//        dto.setId(entity.getId());
//        dto.setAmount(entity.getAmount());
//        dto.setPaidAmount(entity.getPaidAmount());
//        dto.setPendingAmount(entity.getPendingAmount());
//        dto.setStatus(entity.getStatus());
//
//        if (entity.getPatientAdmission() != null) {
//            dto.setAdmissionId(entity.getPatientAdmission().getAdmissionId());
//        }
//
//        return dto;
//    }
//}