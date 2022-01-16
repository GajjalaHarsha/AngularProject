package com.cts.hospitalnearby.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {

	private String userName;
	private String doctorName;
	private String department;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [userName=" + userName + ", doctorName=" + doctorName + ", department=" + department
				+ ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + "]";
	}

}
