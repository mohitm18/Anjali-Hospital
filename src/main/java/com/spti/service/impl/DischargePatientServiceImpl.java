package com.spti.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spti.dao.AdmitPatientRepository;
import com.spti.dao.DischargePatientRepository;
import com.spti.dao.PatientRepository;
import com.spti.dao.TreatmentRepository;
import com.spti.dto.patient.AdmitPatientResponseDto;
import com.spti.dto.patient.DischargePatientRequestDto;
import com.spti.dto.patient.DischargePatientResponseDto;
import com.spti.dto.patient.PatientResponseDto;
import com.spti.entity.AdmitPatient;
import com.spti.entity.DischargePatient;
import com.spti.entity.Patient;
import com.spti.entity.Treatment;
import com.spti.mapper.patient.AdmitPatientMapper;
import com.spti.mapper.patient.DischargePatientMapper;
import com.spti.mapper.patient.PatientMapper;
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

     @Autowired
    private PatientMapper patientMapper;

     @Autowired
      private AdmitPatientMapper admitPatientMapper;

      @Autowired
      private TreatmentRepository treatmentRepository;


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
                optAdmit.get().setDischargedAt(dto.getDischargedAt());
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
    }
     public List<DischargePatientResponseDto> getAllDischargePatientsData(){
                 
                List<DischargePatientResponseDto> dto = new ArrayList<>();

           try{
                List<DischargePatient> dischargePatients = (List<DischargePatient>) dischargePatientRepository.findAll();

               
                
                for(DischargePatient dischargePatient : dischargePatients) {
                   DischargePatientResponseDto responseDto = dischargePatientMapper.toResponseDto(dischargePatient);
                
                   PatientResponseDto   patientResponseDto = patientMapper.toDto(dischargePatient.getPatient());
                     
                   responseDto.setPatientResponseDto(patientResponseDto);

                   AdmitPatientResponseDto admitPatientResponseDto = admitPatientMapper.toResponseDTO(dischargePatient.getAdmitPatient());
                     responseDto.setAdmitPatientResponseDto(admitPatientResponseDto);
                       
                   
                 // responseDto.setPaymentMode(treatment.getPaymentMode());
                   
                   dto.add(responseDto);
                   
                }
            }catch(Exception e){
                throw new RuntimeException("Failed to retrieve discharge patients data", e);
            }

                
               System.out.println(dto);

                return dto;

 }

}
