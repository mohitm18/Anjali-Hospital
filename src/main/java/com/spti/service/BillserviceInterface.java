package com.spti.service;

import com.spti.dto.patient.BillRequestDto;
import com.spti.dto.patient.BillResponseDto;

import java.util.List;

public interface BillserviceInterface {

    BillResponseDto createBill(BillRequestDto requestDto);

    List<BillResponseDto> getPendingBills();

    void payBill(Long id, Integer amount);


}
