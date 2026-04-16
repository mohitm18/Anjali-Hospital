package com.spti.mapper.patient;

import org.springframework.stereotype.Component;

import com.spti.dto.patient.BillRequestDto;
import com.spti.dto.patient.BillResponseDto;
import com.spti.entity.Bill;

//@Component
//public class BillMapper {
//
//	public Bill toEntity( BillRequestDto billRequestDto ) {
//		Bill bill = new Bill();
//		bill.setId( billRequestDto.getId() );
//		bill.setAmount( billRequestDto.getAmount() );
//		bill.setDiscount( billRequestDto.getDiscount() );
//		bill.setFinalBill( billRequestDto.getFinalBill() );
//		bill.setPaidAmount( billRequestDto.getPaidAmount() );
//		bill.setPendingAmount( billRequestDto.getPendingAmount() );
//		bill.setStatus( billRequestDto.getStatus() );
//		//bill.setPatientAdmission( new PatientAdmission( billRequestDto.getAdmissionId() ) ); // Assuming PatientAdmission constructor with id
//		return bill;
//	}
//
//	public BillResponseDto toDto( Bill bill ) {
//		BillResponseDto billResponseDto = new BillResponseDto();
//		billResponseDto.setId( bill.getId() );
//		billResponseDto.setAmount( bill.getAmount() );
//		billResponseDto.setDiscount( bill.getDiscount() );
//		billResponseDto.setFinalBill( bill.getFinalBill() );
//		billResponseDto.setPaidAmount( bill.getPaidAmount() );
//		billResponseDto.setPendingAmount( bill.getPendingAmount() );
//		billResponseDto.setStatus( bill.getStatus() );
//		billResponseDto.setAdmissionId( bill.getPatientAdmission().getAdmissionId() );
//		return billResponseDto;
//	}
//
//}
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