package com.cts.hospitalnearby.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.hospitalnearby.entity.Appointments;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {

	@Query(value = "select * from Appointments where doctor_id= :id and appointment_date= :date and appointment_time between subtime( :time, \"00:30:00\") and addtime( :time, \"00:30:00\")", nativeQuery = true)
	List<Appointments> getAppointmentWithIn30Minutes(@Param("id") Long id, @Param("date") LocalDate date,
			@Param("time") LocalTime time);

	@Transactional
	@Modifying
	@Query(value = "delete from Appointments where doctor_id= :id", nativeQuery = true)
	void deleteDoctorId(@Param("id") Long id);
}
