package com.spti.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "patients_opd_history")
public class PatientOPDHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "seen_by_doctor")
    private String seenByDoctor;

    @Column(name = "treatment")
    private String treatment;

    // Use Double for financial calculations
    //my new changes
    @Column(name = "bill")
    private Double bill;

    // new changes
    @Column(name = "paid_bill")
    private Double paidBill;

    @Column(name = "pending_amount")
    private Double pendingAmount;

    @Column(name = "bill_Status")
    private String billStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "date_of_treatment")
    private LocalDateTime dateOfTreatment;

    @Column(name = "treatment_date")
    private LocalDate treatmentDate;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "note")
    private String note;

    @ManyToOne(targetEntity = Diagnosis.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private Diagnosis diagnosis;


}