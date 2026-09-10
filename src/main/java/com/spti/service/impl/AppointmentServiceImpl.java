// package com.spti.service.impl;

// import java.time.LocalDate;
// import java.time.ZoneId;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.spti.dao.AppointmentRepository;
// import com.spti.dao.BranchDao;
// //import com.spti.dao.DoctorRepository;
// import com.spti.dao.PatientRepository;
// import com.spti.dao.StaffRepository;
// import com.spti.dto.appointments.AppointmentRequestDto;
// import com.spti.dto.appointments.AppointmentResponseDto;
// import com.spti.entity.Appointment;
// import com.spti.entity.Branch;
// //import com.spti.entity.Doctor;
// import com.spti.entity.Patient;
// import com.spti.entity.Staff;
// import com.spti.mapper.appointments.AppointmentMapper;
// import com.spti.service.AppointmentService;

// @Service
// public class AppointmentServiceImpl implements AppointmentService {

// 	@Autowired
// 	private AppointmentRepository appointmentRepository;

// 	@Autowired
// 	private BranchDao branchDao;

// 	@Autowired
// 	private AppointmentMapper appointmentMapper;

// 	@Autowired
// 	private PatientRepository patientRepository;

// 	@Autowired
// 	private StaffRepository staffRepository;




// 	@Override
//    public boolean addAppointment(AppointmentRequestDto dto) {

//     try {

//         Appointment entity = appointmentMapper.toEntity(dto);

//         Optional<Branch> branchOpt =
//                 branchDao.findById(dto.getBranch());

//         if (!branchOpt.isPresent()) {
//             System.out.println("Branch NOT FOUND");
//             return false;
//         }

//         Optional<Patient> patientOpt =
//                 patientRepository.findById(dto.getPatientId());

//         if (!patientOpt.isPresent()) {
//             System.out.println("Patient NOT FOUND");
//             return false;
//         }

//         Optional<Staff> staffOpt =
//                 staffRepository.findById(dto.getDoctorId());

//         if (!staffOpt.isPresent()) {
//             System.out.println("DOCTOR NOT FOUND: " + dto.getDoctorId());
//             return false;
//         }

//         System.out.println("Doctor found: "
//                 + staffOpt.get().getId()
//                 + " - "
//                 + staffOpt.get().getFirstName());

//         entity.setBranch(branchOpt.get());
//         entity.setPatient(patientOpt.get());
//         entity.setStaff(staffOpt.get());

//         System.out.println("Entity Staff ID BEFORE SAVE = "
//                 + (entity.getStaff() != null
//                 ? entity.getStaff().getId()
//                 : null));

//         appointmentRepository.save(entity);

//         System.out.println("========== SAVED ==========");

//         return true;

//     } catch (Exception e) {
//         e.printStackTrace();
//     }

//     return false;
// }

// @Override
// public List<AppointmentResponseDto> getTodaysAppointments(int branchId) {

//     LocalDate localDate = LocalDate.now();

//     Date today = Date.from(
//         localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
//     );

//     List<Appointment> list =
//         appointmentRepository.findTodayAppointments(today);

//     List<AppointmentResponseDto> dtos =
//         appointmentMapper.toList(list);

//     for (AppointmentResponseDto dto : dtos) {

//         if (dto.getDoctorId() != null) {

//             Optional<Staff> staffOpt =
//                 staffRepository.findById(dto.getDoctorId());

//             if (staffOpt.isPresent()) {

//                 Staff staff = staffOpt.get();

//                 dto.setDoctorName(
//                     staff.getFirstName() + " " +
//                     staff.getLastName()
//                 );
//             }
//         }
//     }

//     return dtos;
// }
// // 	@Override
// //     public List<AppointmentResponseDto> getTodaysAppointments(int branchId) {

// //     LocalDate localDate = LocalDate.now();

// //     Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

// //     List<Appointment> list = appointmentRepository.findTodayAppointments(today);

// //     List<AppointmentResponseDto> dtos =appointmentMapper.toList(list);
// //     for (AppointmentResponseDto dto : dtos) {

// //         // Get doctor from STAFF table
// //         if (dto.getDoctorId() != null) {

// //             Optional<Staff> staffOpt =staffRepository.findById(dto.getDoctorId());

// //             if (staffOpt.isPresent()) {
// //                 Staff staff = staffOpt.get();
// //                 dto.setDoctorName(staff.getFirstName() + " " + staff.getLastName());
// //                 System.out.println("Doctor ID: " + staff.getId());
// //                 System.out.println("Doctor Name: "+ staff.getFirstName() + " "+ staff.getLastName());
// //             }
// //         }

// //         // Get patient
// //         if (dto.getPatientId() != null) {

// //             Optional<Patient> patientOpt =patientRepository.findById(dto.getPatientId());

// //             if (patientOpt.isPresent()) {
// //                 Patient patient = patientOpt.get();
// //                 dto.setPatientName(patient.getFirstName()+ " " + patient.getLastName());
// //             }
// //         }
// //     }
// //     return dtos;
// // }

// 	@Override
// 	public AppointmentResponseDto getAppointmentById( Long id ) {
// 		Optional<Appointment> opt = appointmentRepository.findById( id );
// 		if ( opt.isPresent() )
// 			return appointmentMapper.toDto( opt.get() );
// 		return null;
// 	}

// 	@Override
// 	public boolean updateAppointment( @Valid AppointmentRequestDto dto ) {
// 		try {
// 			Appointment entity = appointmentMapper.toEntity( dto );
// 			Optional<Branch> opt = branchDao.findById( dto.getBranch() );
// 			if ( opt.isPresent() ) {
// 				entity.setBranch( opt.get() );
// 			}
// 			// Optional<Doctor> doctorOpt = doctorRepository.findById( dto.getDoctorId() );
// 			// if ( doctorOpt.isPresent() ) {
// 			// 	entity.setDoctor( doctorOpt.get() );
// 			// }

// 			Optional<Patient> patientOpt = patientRepository.findById( dto.getPatientId() );
// 			if ( patientOpt.isPresent() ) {
// 				entity.setPatient( patientOpt.get() );
// 			}
// 			appointmentRepository.save( entity );
// 			return true;
// 		} catch ( Exception e ) {
// 			e.printStackTrace();
// 		}
// 		return false;
// 	}

// 	@Override
// public List<AppointmentResponseDto> getTodaysAppointmentsForADoctor(
//         Long doctorId, int branchId) {

//     LocalDate localDate = LocalDate.now();

//     Date today = Date.from(
//             localDate.atStartOfDay(
//                     ZoneId.systemDefault()
//             ).toInstant()
//     );

//     List<Appointment> list =
//             appointmentRepository.findTodayAppointmentsForDoctor(
//                     today,
//                     doctorId,
//                     branchId
//             );

//     List<AppointmentResponseDto> dtos =
//             appointmentMapper.toList(list);

//     for (AppointmentResponseDto dto : dtos) {

//         // Patient
//         if (dto.getPatientId() != null) {

//             Optional<Patient> patientOpt =
//                     patientRepository.findById(
//                             dto.getPatientId()
//                     );

//             if (patientOpt.isPresent()) {

//                 Patient patient =
//                         patientOpt.get();

//                 dto.setPatientName(
//                         patient.getFirstName()
//                                 + " "
//                                 + patient.getLastName()
//                 );
//             }
//         }

//         // Doctor
//         if (dto.getDoctorId() != null) {

//             Optional<Staff> staffOpt =
//                     staffRepository.findById(
//                             dto.getDoctorId()
//                     );

//             if (staffOpt.isPresent()) {

//                 Staff staff =
//                         staffOpt.get();

//                 dto.setDoctorName(
//                         staff.getFirstName()
//                                 + " "
//                                 + staff.getLastName()
//                 );
//             }
//         }
//     }

//     return dtos;
// }

//     @Override
// public boolean updateAppointmentStatus(Long id, String status) {

//     try {

//         Optional<Appointment> appointmentOpt =
//                 appointmentRepository.findById(id);

//         if (!appointmentOpt.isPresent()) {
//             System.out.println("Appointment NOT FOUND: " + id);
//             return false;
//         }

//         Appointment appointment = appointmentOpt.get();

//         if (status == null || status.trim().isEmpty()) {
//             return false;
//         }

//         status = status.trim().toUpperCase();

//         if (!status.equals("PENDING")
//                 && !status.equals("COMPLETED")
//                 && !status.equals("CANCELLED")) {

//             System.out.println("Invalid appointment status: " + status);
//             return false;
//         }

//         appointment.setStatus(status);

//         appointmentRepository.save(appointment);

//         System.out.println(
//                 "Appointment " + id +
//                 " status updated to " + status);

//         return true;

//     } catch (Exception e) {
//         e.printStackTrace();
//     }
//     return false;
// }

// @Override
// public List<AppointmentResponseDto> getFilteredAppointmentsForDoctor(
//         String filter,
//         Long doctorId,
//         int branchId) {
   

//     LocalDate today = LocalDate.now();

//     Date startDate;
//     Date endDate;

//     List<Appointment> list;

//     switch (filter.toLowerCase()) {

//         case "today":

//             startDate = Date.from(
//                     today.atStartOfDay(
//                             ZoneId.systemDefault()
//                     ).toInstant()
//             );

//             endDate = Date.from(
//                     today.plusDays(1)
//                             .atStartOfDay(
//                                     ZoneId.systemDefault()
//                             ).toInstant()
//             );

//             list = appointmentRepository
//                     .findAppointmentsForDoctorBetween(
//                             startDate,
//                             endDate,
//                             doctorId,
//                             branchId
//                     );

//             break;


//         case "weekly":

//             LocalDate weekStart =
//                     today.with(
//                             java.time.DayOfWeek.MONDAY
//                     );

//             LocalDate weekEnd =
//                     weekStart.plusDays(7);

//             startDate = Date.from(
//                     weekStart.atStartOfDay(
//                             ZoneId.systemDefault()
//                     ).toInstant()
//             );

//             endDate = Date.from(
//                     weekEnd.atStartOfDay(
//                             ZoneId.systemDefault()
//                     ).toInstant()
//             );

//             list = appointmentRepository
//                     .findAppointmentsForDoctorBetween(
//                             startDate,
//                             endDate,
//                             doctorId,
//                             branchId
//                     );

//             break;


//         case "monthly":
//             LocalDate monthStart =
//                     today.withDayOfMonth(1);

//             LocalDate nextMonthStart =
//                     monthStart.plusMonths(1);

//             startDate = Date.from(
//                     monthStart.atStartOfDay(
//                             ZoneId.systemDefault()
//                     ).toInstant()
//             );

//             endDate = Date.from(
//                     nextMonthStart.atStartOfDay(
//                             ZoneId.systemDefault()
//                     ).toInstant()
//             );

//             list = appointmentRepository
//                     .findAppointmentsForDoctorBetween(
//                             startDate,
//                             endDate,
//                             doctorId,
//                             branchId
//                     );

//             break;


//         case "upcoming":

//             Date currentDate = new Date();

//             list = appointmentRepository
//                     .findUpcomingAppointmentsForDoctor(
//                             currentDate,
//                             doctorId,
//                             branchId
//                     );

//             break;


//         default:

//             throw new IllegalArgumentException(
//                     "Invalid appointment filter: " + filter
//             );
//     }


//     List<AppointmentResponseDto> dtos =
//             appointmentMapper.toList(list);


//     // Patient name + Doctor name
//     for (AppointmentResponseDto dto : dtos) {

//         // Patient
//         if (dto.getPatientId() != null) {

//             Optional<Patient> patientOpt =
//                     patientRepository.findById(
//                             dto.getPatientId()
//                     );

//             if (patientOpt.isPresent()) {

//                 Patient patient =
//                         patientOpt.get();

//                 dto.setPatientName(
//                         patient.getFirstName()
//                                 + " "
//                                 + patient.getLastName()
//                 );
//             }
//         }


//         // Doctor
//         if (dto.getDoctorId() != null) {

//             Optional<Staff> staffOpt =
//                     staffRepository.findById(
//                             dto.getDoctorId()
//                     );

//             if (staffOpt.isPresent()) {

//                 Staff staff =
//                         staffOpt.get();

//                 dto.setDoctorName(
//                         staff.getFirstName()
//                                 + " "
//                                 + staff.getLastName()
//                 );
//             }
//         }
//     }

//     return dtos;
// }



// }
package com.spti.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spti.dao.AppointmentRepository;
import com.spti.dao.BranchDao;
import com.spti.dao.PatientRepository;
import com.spti.dao.StaffRepository;
import com.spti.dto.appointments.AppointmentRequestDto;
import com.spti.dto.appointments.AppointmentResponseDto;
import com.spti.entity.Appointment;
import com.spti.entity.Branch;
import com.spti.entity.Patient;
import com.spti.entity.Staff;
import com.spti.mapper.appointments.AppointmentMapper;
import com.spti.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private AppointmentMapper appointmentMapper;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private StaffRepository staffRepository;


	@Override
	public boolean addAppointment(AppointmentRequestDto dto) {

		try {

			Appointment entity = appointmentMapper.toEntity(dto);

			Optional<Branch> branchOpt = branchDao.findById(dto.getBranch());

			if (!branchOpt.isPresent()) {
				System.out.println("Branch NOT FOUND");
				return false;
			}

			Optional<Patient> patientOpt = patientRepository.findById(dto.getPatientId());

			if (!patientOpt.isPresent()) {
				System.out.println("Patient NOT FOUND");
				return false;
			}

			Optional<Staff> staffOpt = staffRepository.findById(dto.getDoctorId());

			if (!staffOpt.isPresent()) {
				System.out.println("DOCTOR NOT FOUND: " + dto.getDoctorId());
				return false;
			}

			System.out.println("Doctor found: "
					+ staffOpt.get().getId()
					+ " - "
					+ staffOpt.get().getFirstName());

			entity.setBranch(branchOpt.get());
			entity.setPatient(patientOpt.get());
			entity.setStaff(staffOpt.get());

			appointmentRepository.save(entity);

			System.out.println("========== SAVED ==========");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<AppointmentResponseDto> getTodaysAppointments(int branchId) {

		LocalDate localDate = LocalDate.now();

		Date today = Date.from(
			localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
		);

		List<Appointment> list =
			appointmentRepository.findTodayAppointments(today);

		List<AppointmentResponseDto> dtos =
			appointmentMapper.toList(list);

		for (AppointmentResponseDto dto : dtos) {

			if (dto.getDoctorId() != null) {

				Optional<Staff> staffOpt =
					staffRepository.findById(dto.getDoctorId());

				if (staffOpt.isPresent()) {

					Staff staff = staffOpt.get();

					dto.setDoctorName(
						staff.getFirstName() + " " +
						staff.getLastName()
					);
				}
			}
		}

		return dtos;
	}

	@Override
	public AppointmentResponseDto getAppointmentById( Long id ) {
		Optional<Appointment> opt = appointmentRepository.findById( id );
		if ( opt.isPresent() )
			return appointmentMapper.toDto( opt.get() );
		return null;
	}

	@Override
	public boolean updateAppointment( @Valid AppointmentRequestDto dto ) {
		try {
			Appointment entity = appointmentMapper.toEntity( dto );
			Optional<Branch> opt = branchDao.findById( dto.getBranch() );
			if ( opt.isPresent() ) {
				entity.setBranch( opt.get() );
			}

			Optional<Patient> patientOpt = patientRepository.findById( dto.getPatientId() );
			if ( patientOpt.isPresent() ) {
				entity.setPatient( patientOpt.get() );
			}
			appointmentRepository.save( entity );
			return true;
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AppointmentResponseDto> getTodaysAppointmentsForADoctor(
			Long doctorId, int branchId) {

		LocalDate localDate = LocalDate.now();

		Date today = Date.from(
				localDate.atStartOfDay(
						ZoneId.systemDefault()
				).toInstant()
		);

		List<Appointment> list =
				appointmentRepository.findTodayAppointmentsForDoctor(
						today,
						doctorId,
						branchId
				);

		List<AppointmentResponseDto> dtos =
				appointmentMapper.toList(list);

		for (AppointmentResponseDto dto : dtos) {

			if (dto.getPatientId() != null) {

				Optional<Patient> patientOpt =
						patientRepository.findById(
								dto.getPatientId()
						);

				if (patientOpt.isPresent()) {

					Patient patient = patientOpt.get();

					dto.setPatientName(
							patient.getFirstName()
									+ " "
									+ patient.getLastName()
					);
				}
			}

			if (dto.getDoctorId() != null) {

				Optional<Staff> staffOpt =
						staffRepository.findById(
								dto.getDoctorId()
						);

				if (staffOpt.isPresent()) {

					Staff staff = staffOpt.get();

					dto.setDoctorName(
							staff.getFirstName()
									+ " "
									+ staff.getLastName()
					);
				}
			}
		}

		return dtos;
	}

	@Override
	public boolean updateAppointmentStatus(Long id, String status) {

		try {

			Optional<Appointment> appointmentOpt =
					appointmentRepository.findById(id);

			if (!appointmentOpt.isPresent()) {
				System.out.println("Appointment NOT FOUND: " + id);
				return false;
			}

			Appointment appointment = appointmentOpt.get();

			if (status == null || status.trim().isEmpty()) {
				return false;
			}

			status = status.trim().toUpperCase();

			if (!status.equals("PENDING")
					&& !status.equals("COMPLETED")
					&& !status.equals("CANCELLED")) {

				System.out.println("Invalid appointment status: " + status);
				return false;
			}

			appointment.setStatus(status);

			appointmentRepository.save(appointment);

			System.out.println(
					"Appointment " + id +
					" status updated to " + status);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AppointmentResponseDto> getFilteredAppointmentsForDoctor(
			String filter,
			Long doctorId,
			int branchId) {

		LocalDate today = LocalDate.now();

		LocalDateTime startDate;
		LocalDateTime endDate;

		List<Appointment> list;

		switch (filter.toLowerCase()) {

			case "today":

				startDate = today.atStartOfDay();
				endDate = today.plusDays(1).atStartOfDay();

				list = appointmentRepository
						.findAppointmentsForDoctorBetween(
								startDate,
								endDate,
								doctorId,
								branchId
						);

				break;


			case "weekly":

				LocalDate weekStart =
						today.with(java.time.DayOfWeek.MONDAY);

				LocalDate weekEnd =
						weekStart.plusDays(7);

				startDate = weekStart.atStartOfDay();
				endDate = weekEnd.atStartOfDay();

				list = appointmentRepository
						.findAppointmentsForDoctorBetween(
								startDate,
								endDate,
								doctorId,
								branchId
						);

				break;


			case "monthly":

				LocalDate monthStart =
						today.withDayOfMonth(1);

				LocalDate nextMonthStart =
						monthStart.plusMonths(1);

				startDate = monthStart.atStartOfDay();
				endDate = nextMonthStart.atStartOfDay();

				list = appointmentRepository
						.findAppointmentsForDoctorBetween(
								startDate,
								endDate,
								doctorId,
								branchId
						);

				break;


			case "upcoming":

				LocalDateTime currentDateTime = LocalDateTime.now();

				list = appointmentRepository
						.findUpcomingAppointmentsForDoctor(
								currentDateTime,
								doctorId,
								branchId
						);

				break;


			default:

				throw new IllegalArgumentException(
						"Invalid appointment filter: " + filter
				);
		}


		List<AppointmentResponseDto> dtos =
				appointmentMapper.toList(list);


		for (AppointmentResponseDto dto : dtos) {

			if (dto.getPatientId() != null) {

				Optional<Patient> patientOpt =
						patientRepository.findById(
								dto.getPatientId()
						);

				if (patientOpt.isPresent()) {

					Patient patient = patientOpt.get();

					dto.setPatientName(
							patient.getFirstName()
									+ " "
									+ patient.getLastName()
					);
				}
			}


			if (dto.getDoctorId() != null) {

				Optional<Staff> staffOpt =
						staffRepository.findById(
								dto.getDoctorId()
						);

				if (staffOpt.isPresent()) {

					Staff staff = staffOpt.get();

					dto.setDoctorName(
							staff.getFirstName()
									+ " "
									+ staff.getLastName()
					);
				}
			}
		}

		return dtos;
	}

}