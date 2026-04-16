package com.spti.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spti.dao.AdmitPatientRepository;
import com.spti.dao.DischargePatientRepository;
import com.spti.dao.PatientRepository;
import com.spti.dto.patient.DischargePatientRequestDto;
import com.spti.entity.AdmitPatient;
import com.spti.entity.DischargePatient;
import com.spti.entity.Patient;
import com.spti.mapper.patient.DischargePatientMapper;
import com.spti.service.DischargePatientService;

@Service
public class DischargePatientServiceImpl implements DischargePatientService {

    @Autowired
    private DischargePatientMapper dischargePatientMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    AdmitPatientRepository admitPatientRepository;

    @Autowired
    private DischargePatientRepository dischargePatientRepository;

    @Override
    public boolean dischargePatienAdd(DischargePatientRequestDto dto) {

        try {

            Optional<Patient> optPatient =
                    patientRepository.findById(dto.getPatientId());

            Optional<AdmitPatient> optAdmit =
                    admitPatientRepository
                            .findByPatient_idAndAdmitDischargeStatus(
                                    dto.getPatientId(), "Admit");


            if (optPatient.isPresent() && optAdmit.isPresent()) {

                DischargePatient discharge =
                        dischargePatientMapper.toEntity(dto);


                discharge.setPatient(optPatient.get());
                discharge.setAdmitPatient(optAdmit.get());


                dischargePatientRepository.save(discharge);


                AdmitPatient admit = optAdmit.get();
                admit.setAdmitDischargeStatus("Discharge");
                admitPatientRepository.save(admit);

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }}
