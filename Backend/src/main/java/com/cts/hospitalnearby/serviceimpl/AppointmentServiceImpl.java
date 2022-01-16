package com.cts.hospitalnearby.serviceimpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospitalnearby.dto.AppointmentDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.exception.UserNotFoundException;
import com.cts.hospitalnearby.repository.AppointmentRepository;
import com.cts.hospitalnearby.service.AppointmentService;
import com.cts.hospitalnearby.service.DoctorService;
import com.cts.hospitalnearby.service.UserService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	UserService uService;

	@Autowired
	DoctorService dService;

	public Appointments bookAppointment(Long userId, Long doctorId, AppointmentDTO appointment)
			throws UserNotFoundException {
		Appointments appointments = new Appointments();
		if (this.checkIsDateValid(appointment.getAppointmentDate())) {
			List<Appointments> appointmentWithIn30Minutes = appointmentRepository.getAppointmentWithIn30Minutes(
					doctorId, appointment.getAppointmentDate(), appointment.getAppointmentTime());
			if (appointmentWithIn30Minutes.isEmpty()) {
				appointments.setAppointmentDate(appointment.getAppointmentDate());
				appointments.setAppointmentTime(appointment.getAppointmentTime());
				User user = uService.getUser(userId);
				Doctors doctor = dService.getDoctorUsingId(doctorId);
				appointments.setDoctors(doctor);
				appointments.setUser(user);
				return appointmentRepository.save(appointments);
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public void deleteDoctor(Long id) {
		appointmentRepository.deleteDoctorId(id);
	}

	public boolean checkIsDateValid(LocalDate appointmentDate) {

		LocalDate today = LocalDate.now();
		if (appointmentDate.isEqual(today) || appointmentDate.isAfter(today)) {
			Long days = ChronoUnit.DAYS.between(today, appointmentDate);
			return days <= 2;
		}
		return false;
	}

}
