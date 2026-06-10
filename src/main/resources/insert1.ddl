--Login Table

INSERT INTO `hospital`.`login` (`id`, `username`, `password`, `role`) VALUES ('1', 'riya@yopmail.com', 'Demo_1234', 'Receptionist');
INSERT INTO `hospital`.`branch` (`id`, `name`, `address`) VALUES ('1', 'BhadGao', 'Jalgao');

--
--Role Table Static 

INSERT IGNORE INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'DOCTOR');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'PATIENT');
INSERT IGNORE INTO roles (id, name) VALUES (4, 'PHARMACIST');
INSERT IGNORE INTO roles (id, name) VALUES (5, 'RECEPTIONIST');
INSERT IGNORE INTO roles (id, name) VALUES (6, 'WARD_BOY');
INSERT IGNORE INTO roles (id, name) VALUES (7, 'NURSE');
INSERT IGNORE INTO roles (id, name) VALUES (8, 'VENDOR');
INSERT IGNORE INTO roles (id, name) VALUES (9, 'LAB_TECHNICIAN');
INSERT IGNORE INTO roles (id, name) VALUES (10, 'ACCOUNTANT');

--