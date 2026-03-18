package com.spti.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spti.billingUtility.BillingUtility;
import com.spti.dao.OpdPatientHistoryRepository;
import com.spti.dao.PatientRepository;
import com.spti.dto.patient.PatientOPDHistoryRequestDTO;
import com.spti.dto.patient.PatientOPDHistoryResponseDto;
import com.spti.entity.Patient;
import com.spti.entity.PatientOPDHistory;
import com.spti.mapper.patient.PatientOPDHistoryMapper;
import com.spti.service.OpdPatientHistoryService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpdPatientHistoryServiceImpl implements OpdPatientHistoryService {

    @Autowired
    private OpdPatientHistoryRepository opdPatientHistoryRepository;

    @Autowired
    private PatientOPDHistoryMapper opdHistoryMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BillingUtility billingUtility;

    @Override
    public List<PatientOPDHistoryResponseDto> opdPatientHistory(String disease, String todayrecord, String ages) {
        LocalDate startDate;
        LocalDate endDate;
        if (todayrecord.equalsIgnoreCase("todaypatient")) {
            LocalDate date = LocalDate.now();
            return opdHistoryMapper.gettoResponseList(opdPatientHistoryRepository.findByTreatmentDateAndDiagnosisAndAge(date, disease, ages));
        } else if (todayrecord.equalsIgnoreCase("weekpatient")) {
            endDate = LocalDate.now();
            startDate = endDate.minusDays(6);
        } else if (todayrecord.equalsIgnoreCase("monthlypatient")) {
            endDate = LocalDate.now();
            startDate = endDate.minusDays(30);
        } else if (todayrecord.equalsIgnoreCase("AllPatient")) {
            endDate = LocalDate.now();
            startDate = endDate.minusDays(360);
        } else {
            return Collections.emptyList();
        }
        return opdHistoryMapper.gettoResponseList(opdPatientHistoryRepository.findByTreatmentDateBetweenAndDiagnosisAndAge(startDate, endDate, disease, ages));
    }
    @Override
    public List<PatientOPDHistoryResponseDto> getPatientOpdHistory(Long patientId) {
        Optional<Patient> opt = patientRepository.findById(patientId);
        if (opt.isPresent())
            return opdHistoryMapper.toResponseList(opdPatientHistoryRepository.findByPatientOrderByIdDesc(opt.get()));
        return null;
    }
//new changes for paid_bill
    @Override
    public boolean addOpdHistory(PatientOPDHistoryRequestDTO dto) {
        // 1. Map the basic fields
        PatientOPDHistory entity = opdHistoryMapper.toEntity(dto);
        // 2. MANUALLY set the billing fields to ensure they aren't lost by the mapper
        entity.setBill(dto.getBill());
        entity.setPaidBill(dto.getPaidBill());
        entity.setPendingAmount(dto.getPendingAmount());
        entity.setBillStatus(dto.getBillStatus());
        Optional<Patient> opt = patientRepository.findById(dto.getPatientId());
        if (opt.isPresent()) {
            entity.setPatient(opt.get());
            // 3. Set treatment date if not already set
            if(entity.getTreatmentDate() == null) {
                entity.setTreatmentDate(LocalDate.now());
            }
            opdPatientHistoryRepository.save(entity);
            return true;
        }
        return false;
    }
    @Override
    public PatientOPDHistoryResponseDto opdPatienBill(String todayrecord) {
        if (todayrecord.equalsIgnoreCase("Today OpdPatient And Bill")) {
            LocalDate date = LocalDate.now();
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListByTreatmentDate(date);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
        else if (todayrecord.equalsIgnoreCase("weeklyrecord")) {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(6);
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListBetweenTreatmentDates(startDate, enddate);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        } else {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(30);
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListBetweenTreatmentDates(startDate, enddate);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
    }
    @Override
    public List<PatientOPDHistoryResponseDto> GetTodayOpdPatient(String todayrecord) {
        if (todayrecord.equalsIgnoreCase("Today OpdPatient And Bill")) {
            LocalDate date = LocalDate.now();
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findByTreatmentDate(date);
            List<PatientOPDHistoryResponseDto> dto = (opdHistoryMapper.toResponseList(entityPage));
            return billingUtility.getPaidBill(dto);
        }
        else if (todayrecord.equalsIgnoreCase("weeklyrecord")) {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(6);
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListBetweenTreatmentDates(startDate, enddate);
            return billingUtility.getPaidBill(opdHistoryMapper.toResponseList(entityPage));
        }
        else {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(30);
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListBetweenTreatmentDates(startDate, enddate);
            return billingUtility.getPaidBill(opdHistoryMapper.toResponseList(entityPage));
        }
    }
    @Override
    public PatientOPDHistoryResponseDto getOpdRecordByid(Long id) {
        Optional<PatientOPDHistory> entity = opdPatientHistoryRepository.findById(id);
        PatientOPDHistoryResponseDto dto = opdHistoryMapper.toResponseDTO(entity.get());
        return billingUtility.getPaidBillSingleOpd(dto);
    }

    @Override
    public PatientOPDHistoryResponseDto opdPatienBillOnlineDashbord(String todayrecord) {
        if (todayrecord.equalsIgnoreCase("Today OpdPatient And Bill")) {
            LocalDate date = LocalDate.now();
            String paymentType = "Online";
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListByTreatmentDateAndPaymentType(date, paymentType);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
        else if (todayrecord.equalsIgnoreCase("weeklyrecord")) {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(6);
            String paymentType = "Online";
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findByTreatmentDateBetweenAndPaymentType(startDate, enddate, paymentType);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
        else {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(30);
            String paymentType = "Online";
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findByTreatmentDateBetweenAndPaymentType(startDate, enddate, paymentType);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
    }

    @Override
    public PatientOPDHistoryResponseDto opdPatienBillCashDashbord(String todayrecord) {
        if (todayrecord.equalsIgnoreCase("Today OpdPatient And Bill")) {
            LocalDate date = LocalDate.now();
            String paymentType = "Cash";
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findPatientOPDHistoryListByTreatmentDateAndPaymentType(date, paymentType);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
        else if (todayrecord.equalsIgnoreCase("weeklyrecord")) {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(6);
            String paymentType = "Cash";
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findByTreatmentDateBetweenAndPaymentType(startDate, enddate, paymentType);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
        else {
            LocalDate enddate = LocalDate.now();
            LocalDate startDate = enddate.minusDays(30);
            String paymentType = "Cash";
            List<PatientOPDHistory> entityPage = opdPatientHistoryRepository.findByTreatmentDateBetweenAndPaymentType(startDate, enddate, paymentType);
            List<PatientOPDHistoryResponseDto> dtoList = opdHistoryMapper.toResponseList(entityPage);
            return billingUtility.totalBillOfOPDPatient(dtoList);
        }
    }

    @Transactional
    public boolean updatePaidBill(Long id, Double bill, Double paidBill, Double pendingAmount, String paymentType) {
         PatientOPDHistory patientOPDHistory = opdPatientHistoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bill not found"));
         Double totalBill = patientOPDHistory.getBill();
         Double previousPaid = patientOPDHistory.getPaidBill() == null ? 0 : patientOPDHistory.getPaidBill();
         Double currentPending = totalBill - previousPaid;

         // ❗ Validation: do not allow more than pending
         if (paidBill > currentPending) {
           throw new RuntimeException("Paid amount cannot be greater than pending amount");
    }
         Double newPaidTotal = previousPaid + paidBill;
         Double pending = totalBill - newPaidTotal;
         patientOPDHistory.setPaidBill(newPaidTotal);
         patientOPDHistory.setPendingAmount(Math.max(0, pending));
        if (pending <= 0) {
           patientOPDHistory.setBillStatus("Paid");
    }
        else {
           patientOPDHistory.setBillStatus("Pending");
    }
           patientOPDHistory.setPaymentType(paymentType);
           opdPatientHistoryRepository.save(patientOPDHistory);
           return true;
    }
}
