UPDATE `spti`.`staff` SET `role` = 'HR' WHERE (`id` = '1');

--Anu Muralasetti
CREATE TABLE `treatment_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admittance_id` int DEFAULT NULL,
  `medicine_name` varchar(32) DEFAULT NULL,
  `medicine_time` varchar(128) DEFAULT NULL,
  `treatment_date` date DEFAULT NULL,
  `bill` int DEFAULT NULL,
  `bill_paid` varchar(32) DEFAULT NULL,
  `payment_mode` varchar(32) DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  
  PRIMARY KEY (`id`)
)

alter table hospital.admit_patient add column
`mLC_No` int ,
 add column `IPD_No` int ,
 add column `consultatnt` varchar(45) ,
 add column `diagnosis` varchar(45) ,
 add column `clinical_History` varchar(45) ,
 add column `amount_paid` int ,
 add column `amount_tobepaid` int ,
 add column `discharged_at` datetime  NULL