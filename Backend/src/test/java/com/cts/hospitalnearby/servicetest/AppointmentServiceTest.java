package com.cts.hospitalnearby.servicetest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.hospitalnearby.dto.AppointmentDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.exception.UserNotFoundException;
import com.cts.hospitalnearby.repository.AppointmentRepository;
import com.cts.hospitalnearby.service.AppointmentService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
class AppointmentServiceTest {

	@Autowired
	private AppointmentRepository appRepository;

	@Autowired
	private AppointmentService appService;

	@Test
	@Order(1)
	@DisplayName(value = "Booking Appointment")
	void bookAppointment() {
		AppointmentDTO app = new AppointmentDTO();
		app.setAppointmentDate(LocalDate.now());
		app.setAppointmentTime(LocalTime.of(12, 30));
		Appointments bookAppointment = null;
		try {
			bookAppointment = appService.bookAppointment(1L, 1L, app);
			appRepository.deleteById(bookAppointment.getAppointmentId());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		assertNotNull(bookAppointment);
	}

	@Test
	@Order(2)
	@DisplayName(value = "Booking Appointment at invalid Date")
	void bookAppointmentInvalidTime() {
		AppointmentDTO app = new AppointmentDTO();
		app.setAppointmentDate(LocalDate.now().plusDays(3L));
		app.setAppointmentTime(LocalTime.of(12, 30));
		try {
			Appointments bookAppointment = appService.bookAppointment(1L, 1L, app);
			assertNull(bookAppointment);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
	}
}
