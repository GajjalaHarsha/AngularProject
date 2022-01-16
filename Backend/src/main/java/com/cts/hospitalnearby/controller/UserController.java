package com.cts.hospitalnearby.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospitalnearby.dto.AppointmentDTO;
import com.cts.hospitalnearby.dto.UserDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.exception.UserNotFoundException;
import com.cts.hospitalnearby.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/user/register")
	public UserDTO insertUser(@RequestBody UserDTO user) {
		LOGGER.info("Inside insert user method in User Controller");
		UserDTO user1 = null;
		if (service.isExistInDB(user)) {
			return user1;
		} else {
			User storeuser = new User();
			storeuser.setName(user.getName());
			storeuser.setPassword(user.getPassword());
			storeuser.setEmail(user.getEmail());
			User addUserDetails = service.addUserDetailsInDB(storeuser);
			user1 = this.getUserDTO(addUserDetails);
			return user1;
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/user/login")
	public UserDTO userLoginValidation(@RequestBody UserDTO user) {
		LOGGER.info("Inside userlogin validation method in User Controller");
		UserDTO user1 = null;
		User validUser = service.isValidUser(user);
		if (validUser != null)
			user1 = this.getUserDTO(validUser);
		return user1;

	}

	private UserDTO getUserDTO(User loggeduser) {
		UserDTO user = new UserDTO();
		user.setId(loggeduser.getId());
		user.setName(loggeduser.getName());
		user.setPassword(loggeduser.getPassword());
		user.setEmail(loggeduser.getEmail());
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/user/appointments/{id}")

	public List<AppointmentDTO> appointments(@PathVariable("id") Long id) throws UserNotFoundException {
		LOGGER.info("Inside appointments method in User Controller");
		List<Appointments> apps = service.getAppointments(id);
		List<AppointmentDTO> appointments = null;
		if (apps != null) {
			appointments = new ArrayList<>();
			for (Appointments appointment : apps) {
				AppointmentDTO app = new AppointmentDTO();
				app.setAppointmentDate(appointment.getAppointmentDate());
				app.setAppointmentTime(appointment.getAppointmentTime());
				app.setUserName(appointment.getUser().getName());
				app.setDoctorName(appointment.getDoctors().getDoctorName());
				app.setDepartment(appointment.getDoctors().getSpec().getSplName());
				appointments.add(app);
			}
		}
		return appointments;
	}
}
