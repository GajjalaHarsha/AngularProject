package com.cts.hospitalnearby.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospitalnearby.dto.AppointmentDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.exception.UserNotFoundException;
import com.cts.hospitalnearby.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	AppointmentService service1;
	private static final Logger LOGGER = LogManager.getLogger(AppointmentController.class);

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/appointments/user/{userid}/doctor/{doctorid}")
	public AppointmentDTO saveAppointment(@PathVariable("userid") Long userid, @PathVariable("doctorid") Long doctorid,
			@RequestBody AppointmentDTO appointment) throws UserNotFoundException {
		LOGGER.info("Inside book appointment method in Appointment Controller");
		Appointments appointments = service1.bookAppointment(userid, doctorid, appointment);
		if (appointments != null) {
			return this.getAppointmentDTO(appointments);
		} else {
			return null;

		}
	}

	private AppointmentDTO getAppointmentDTO(Appointments appointments) {

		AppointmentDTO app = new AppointmentDTO();
		app.setAppointmentDate(appointments.getAppointmentDate());
		app.setAppointmentTime(appointments.getAppointmentTime());
		app.setUserName(appointments.getUser().getName());
		app.setDoctorName(appointments.getDoctors().getDoctorName());
		app.setDepartment(appointments.getDoctors().getSpec().getSplName());
		return app;
	}

}
