//package com.spti.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "bills")
//public class Bill {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "admission_id", referencedColumnName = "admission_id")
//    private PatientAdmission patientAdmission;
//
//    // CHANGE Integer → Double
//    private Double amount;
//    private Double discount;
//    private Double finalBill;
//    private Double paidAmount;
//    private Double pendingAmount;
//
//    private String status;
//}