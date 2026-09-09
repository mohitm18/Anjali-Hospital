// package com.spti.dao;

// import java.util.Date;
// import java.util.List;

// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import com.spti.dto.appointments.AppointmentResponseDto;
// import com.spti.entity.Appointment;

// @Repository
// public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

// 	@Query( "SELECT a FROM Appointment a WHERE DATE(a.appointmentDate) = :today" )
// 	List<Appointment> findTodayAppointments( @Param( "today" ) Date today );

// 	// @Query( "SELECT a FROM Appointment a WHERE DATE(a.appointmentDate) = :today AND a.doctor.id = :doctorId" )
// 	// List<Appointment> findTodayAppointmentsForDoctor( @Param( "today" ) Date today, @Param( "doctorId" ) Long doctorId );
    
// 	// @Query("SELECT a FROM Appointment a WHERE DATE(a.appointmentDate)=:today AND a.staff.id=:doctorId")
//         // List<Appointment> findTodayAppointmentsForDoctor(@Param("today") Date today,@Param("doctorId") Long doctorId);


// 	@Query("SELECT a FROM Appointment a " +
//        "WHERE a.appointmentDate >= :startDate " +
//        "AND a.appointmentDate < :endDate " +
//        "AND a.staff.id = :doctorId " +
//        "AND a.branch.id = :branchId " +
//        "ORDER BY a.appointmentDate ASC")
//        List<Appointment> findAppointmentsForDoctorBetween(
//         @Param("startDate") Date startDate,
//         @Param("endDate") Date endDate,
//         @Param("doctorId") Long doctorId,
//         @Param("branchId") int branchId);


//         @Query("SELECT a FROM Appointment a " +
//        "WHERE DATE(a.appointmentDate) = :today " +
//        "AND a.staff.id = :doctorId " +
//        "AND a.branch.id = :branchId")
//         List<Appointment> findTodayAppointmentsForDoctor(
//         @Param("today") Date today,
//         @Param("doctorId") Long doctorId,
//         @Param("branchId") int branchId);



//         @Query("SELECT a FROM Appointment a " +
//        "WHERE a.appointmentDate >= :currentDate " +
//        "AND a.staff.id = :doctorId " +
//        "AND a.branch.id = :branchId " +
//        "ORDER BY a.appointmentDate ASC")
// List<Appointment> findUpcomingAppointmentsForDoctor(
//         @Param("currentDate") Date currentDate,
//         @Param("doctorId") Long doctorId,
//         @Param("branchId") int branchId);
// }


package com.spti.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spti.entity.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	@Query( "SELECT a FROM Appointment a WHERE DATE(a.appointmentDate) = :today" )
	List<Appointment> findTodayAppointments( @Param( "today" ) Date today );

	@Query("SELECT a FROM Appointment a " +
       "WHERE a.appointmentDate >= :startDate " +
       "AND a.appointmentDate < :endDate " +
       "AND a.staff.id = :doctorId " +
       "AND a.branch.id = :branchId " +
       "ORDER BY a.appointmentDate ASC")
    List<Appointment> findAppointmentsForDoctorBetween(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("doctorId") Long doctorId,
        @Param("branchId") int branchId);


    @Query("SELECT a FROM Appointment a " +
       "WHERE DATE(a.appointmentDate) = :today " +
       "AND a.staff.id = :doctorId " +
       "AND a.branch.id = :branchId")
    List<Appointment> findTodayAppointmentsForDoctor(
        @Param("today") Date today,
        @Param("doctorId") Long doctorId,
        @Param("branchId") int branchId);


    @Query("SELECT a FROM Appointment a " +
       "WHERE a.appointmentDate >= :currentDate " +
       "AND a.staff.id = :doctorId " +
       "AND a.branch.id = :branchId " +
       "ORDER BY a.appointmentDate ASC")
    List<Appointment> findUpcomingAppointmentsForDoctor(
        @Param("currentDate") LocalDateTime currentDate,
        @Param("doctorId") Long doctorId,
        @Param("branchId") int branchId);
}