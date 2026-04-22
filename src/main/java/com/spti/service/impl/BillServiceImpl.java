package com.spti.service.impl;

import com.spti.dao.BillRepository;
import com.spti.dao.DischargePatientRepository;
import com.spti.dao.PatientRepository;
import com.spti.dto.patient.BillRequestDto;
import com.spti.dto.patient.BillResponseDto;
import com.spti.entity.Bill;
import com.spti.entity.Patient;
import com.spti.mapper.patient.BillMapper;
import com.spti.mapper.patient.DischargePatientMapper;
import com.spti.service.BillserviceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillserviceInterface {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private DischargePatientRepository dischargePatientRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private DischargePatientMapper dischargemapper;
    @Override
    public BillResponseDto createBill(BillRequestDto dto){

        Bill bill = billMapper.toEntity(dto);
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        bill.setPatient(patient);
        Bill saved = billRepository.save(bill);
        return billMapper.toResponseDto(saved);
    }

    @Override
    public List<BillResponseDto> getPendingBills(){

        List<Bill> bills = billRepository.findByStatus("Pending");

        List<BillResponseDto> list = new ArrayList<>();

        for(Bill bill : bills){
            list.add(billMapper.toResponseDto(bill));
        }

        return list;
    }

    @Override
    public void payBill(Long id, Integer amount){
        Bill bill = billRepository.findById(id).get();
        if(amount > bill.getPendingAmount()){
            throw new RuntimeException("Amount exceeds pending bill");
        }
        int paid = bill.getPaidAmount() + amount;
        int pending = bill.getPendingAmount() - amount;
        bill.setPaidAmount(paid);
        bill.setPendingAmount(pending);

        if(pending <= 0){
            bill.setStatus("Paid");
            bill.setPendingAmount(0);
        }
        billRepository.save(bill);
    }

}
