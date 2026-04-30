DROP SCHEMA IF EXISTS `hospital`;
CREATE SCHEMA `hospital` ;
use `hospital`;

CREATE TABLE `hospital`.`branch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL,
  `address` VARCHAR(128) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `role`  VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


CREATE TABLE hospital.patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender VARCHAR(8),
    address VARCHAR(255),
    age INT,
    phone_number VARCHAR(15),
    email VARCHAR(100),
    branch INT NOT NULL,
    FOREIGN KEY (branch) REFERENCES hospital.branch(id)
);

CREATE TABLE diagnosis (
    id BIGINT NOT NULL AUTO_INCREMENT,
    diagnosis VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE patients_opd_history (
    id BIGINT NOT NULL AUTO_INCREMENT,
    seen_by_doctor VARCHAR(255),
    diagnosis_id BIGINT,
    treatment VARCHAR(255),
    bill VARCHAR(255),
    pending_amount VARCHAR(45),
    bill_status VARCHAR(255),
    date_of_treatment TIMESTAMP,
    patient_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (diagnosis_id) REFERENCES diagnosis(id)
);

ALTER TABLE `hospital`.`patients_opd_history` 
ADD COLUMN `treatment_Date` DATE NULL AFTER `bill_Status`;

ALTER TABLE `hospital`.`patients_opd_history` 
ADD COLUMN `payment_type` VARCHAR(45) NULL AFTER `date_of_treatment`;

ALTER TABLE `hospital`.`patients_opd_history` 
CHANGE COLUMN `treatment` `treatment` VARCHAR(500) NULL DEFAULT NULL ,
CHANGE COLUMN `note` `note` VARCHAR(600) NULL DEFAULT NULL ;


CREATE TABLE `admit_patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admit_Date` timestamp NULL DEFAULT NULL,
  `patient_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `patient_id` (`patient_id`),
  CONSTRAINT `admit_patient` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`)); 

CREATE TABLE `hospital`.`discharge_patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mLC_No` INT NOT NULL,
  `IPD_No` INT NOT NULL,
  `consultatnt` VARCHAR(45) NOT NULL,
  `diagnosis` VARCHAR(45) NOT NULL,
  `clinical_History` VARCHAR(45) NOT NULL,
  `admit_Patient_id` INT NOT NULL,
  `patient_id`INT NOT NULL,
  PRIMARY KEY (`id`),
 FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
 FOREIGN KEY (admit_Patient_id) REFERENCES admit_patient(id)
);


ALTER TABLE `hospital`.`admit_patient` 
ADD COLUMN `Admit_And_Discharge_Status` VARCHAR(45) NOT NULL AFTER `admit_Date`;


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

CREATE TABLE staff (
    id BIGINT NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    email VARCHAR(255),
    experience VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255),
    role VARCHAR(255),
    status VARCHAR(255),
    branch INT,
    PRIMARY KEY (id),

    -- Optional but recommended constraints
    UNIQUE (email),

    -- Foreign Key (assuming branch table has id column)
    CONSTRAINT fk_staff_branch
    FOREIGN KEY (branch) REFERENCES branch(id)
);

ALTER TABLE hospital.login
ADD CONSTRAINT fk_login_staff
FOREIGN KEY (staff_id) REFERENCES staff(id);
