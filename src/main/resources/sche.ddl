DROP SCHEMA IF EXISTS `spti`;

CREATE SCHEMA `spti` ;

use `spti`;

CREATE TABLE `spti`.`branch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL,
  `address` VARCHAR(128) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    specialization VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20),
    address VARCHAR(255),
    experience VARCHAR(20),
    status VARCHAR(20),
    branch INT NOT NULL,
    consultation_fee INT,
    available_days VARCHAR(255),
    available_time_slots VARCHAR(255),
    joining_date TIMESTAMP,
    qualifications VARCHAR(255)
);


ALTER TABLE spti.doctors
ADD CONSTRAINT fk_doctors_branch
FOREIGN KEY (branch)
REFERENCES spti.branch(id);

CREATE TABLE spti.insurance_details (
    insurance_id INT AUTO_INCREMENT PRIMARY KEY,
    policy_number VARCHAR(50),
    provider_name VARCHAR(100),
    expiry_date TIMESTAMP
);

CREATE TABLE spti.patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth TIMESTAMP,
    address VARCHAR(255),
    phone_number VARCHAR(15),
    email VARCHAR(100),
    emergency_contact_name VARCHAR(100),
    emergency_contact_number VARCHAR(15),
    blood_type VARCHAR(5),
    allergies TEXT,
    medical_history TEXT,
    current_medications TEXT,
    vaccination_history TEXT,
    next_of_kin_name VARCHAR(100),
    next_of_kin_relationship VARCHAR(50),
    next_of_kin_phone_number VARCHAR(15),
    occupation VARCHAR(50),
    marital_status VARCHAR(32),
    nationality VARCHAR(50),
    language_spoken VARCHAR(50),
    religion VARCHAR(50),
    preferred_doctor VARCHAR(100),
    admission_date TIMESTAMP,
    discharge_date TIMESTAMP,
    insurance_id INT,
    branch int NOT NULL,
    FOREIGN KEY (insurance_id) REFERENCES insurance_details(insurance_id)
);

ALTER TABLE spti.patients
ADD CONSTRAINT fk_patients_branch
FOREIGN KEY (branch)
REFERENCES spti.branch(id);

CREATE TABLE `doctor_specialization` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE spti.`staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `experience` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `branch` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)

ALTER TABLE `spti`.`patients` 
CHANGE COLUMN `date_of_birth` `date_of_birth` TIMESTAMP NULL DEFAULT NULL ,
CHANGE COLUMN `admission_date` `admission_date` TIMESTAMP NULL DEFAULT NULL ,
CHANGE COLUMN `discharge_date` `discharge_date` TIMESTAMP NULL DEFAULT NULL ;


ALTER TABLE `spti`.`insurance_details` 
CHANGE COLUMN `expiry_date` `expiry_date` TIMESTAMP NULL DEFAULT NULL ;