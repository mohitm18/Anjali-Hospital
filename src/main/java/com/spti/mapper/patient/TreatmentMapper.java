package com.spti.mapper.patient;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.spti.dto.treatment.TreatmentRequest;
import com.spti.dto.treatment.TreatmentResponse;
import com.spti.entity.Treatment;

@Component
public class TreatmentMapper {
	public Treatment toEntity(TreatmentRequest treatmentRequestdto) {
		Treatment treatment=new Treatment();
		treatment.setAdmittanceId(treatmentRequestdto.getAdmittanceId());
		treatment.setMedicineName(treatmentRequestdto.getMedicineName());
		treatment.setMedicineTime(treatmentRequestdto.getMedicineTime());
		treatment.setTreatmentDate(treatmentRequestdto.getTreatmentDate());
		treatment.setBill(treatmentRequestdto.getBill());
		treatment.setBillPaid(treatmentRequestdto.getBillPaid());
		treatment.setPaymentMode(treatmentRequestdto.getPaymentMode());
		treatment.setCreatedBy(treatmentRequestdto.getCreatedBy());
		treatment.setCreatedAt(LocalDateTime.now());
        treatment.setPaidAmount(treatmentRequestdto.getPaidAmount());
        treatment.setAmounttobePaid(treatmentRequestdto.getAmounttobePaid());
		return treatment;
		
	}
	
	public TreatmentResponse toDto(Treatment treatment) {
		TreatmentResponse treatmentResponsedto=new TreatmentResponse();
		treatmentResponsedto.setMedicineName(treatment.getMedicineName());
		treatmentResponsedto.setMedicineTime(treatment.getMedicineTime());
		treatmentResponsedto.setTreatmentDate(treatment.getTreatmentDate());
		treatmentResponsedto.setBill(treatment.getBill());
		treatmentResponsedto.setBillPaid(treatment.getBillPaid());
		treatmentResponsedto.setPaymentMode(treatment.getPaymentMode());
        treatmentResponsedto.setPaidAmount(treatment.getPaidAmount());
        treatmentResponsedto.setAmounttobePaid(treatment.getAmounttobePaid());
		return treatmentResponsedto;
		
	}

}
