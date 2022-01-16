package com.cts.hospitalnearby.service;

import java.time.LocalDate;

import com.cts.hospitalnearby.dto.AppointmentDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.exception.UserNotFoundException;

public interface AppointmentService {

	public Appointments bookAppointment(Long userId, Long doctorId, AppointmentDTO appointments)
			throws UserNotFoundException;

	public void deleteDoctor(Long id);

	public boolean checkIsDateValid(LocalDate appointmentDate);
}
