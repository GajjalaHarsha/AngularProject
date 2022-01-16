package com.cts.hospitalnearby.serviceimpl;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospitalnearby.dto.DoctorDTO;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.repository.DoctorRepository;
import com.cts.hospitalnearby.service.AppointmentService;
import com.cts.hospitalnearby.service.DoctorService;
import com.cts.hospitalnearby.service.SpecializationService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private SpecializationService service;

	@Autowired
	private AppointmentService appService;

	public Doctors insertDoctorsIntoDB(String spec, DoctorDTO doctor) {
		Specialization specialization = service.getSpecializationByName(spec);
		Doctors doctors = new Doctors();
		doctors.setDoctorName(doctor.getDoctorName());
		doctors.setExperience(doctor.getExperience());
		doctors.setNoOfOperationsTaken(doctor.getNoOfOperationsTaken());
		doctors.setOperationsSuccess(doctor.getOperationsSuccess());
		doctors.setSpec(specialization);
		doctors.setSuccessRatio(
				BigDecimal.valueOf((double) doctor.getOperationsSuccess() / doctor.getNoOfOperationsTaken()).setScale(2,
						RoundingMode.HALF_EVEN));
		return doctorRepository.save(doctors);
	}

	public List<Doctors> getDoctorsOfSpecialization(String spec) {
		Specialization specializationByName = this.getSpecialization(spec);
		return specializationByName.getDoctors();
	}

	public Doctors getDoctorUsingId(Long id) {
		Doctors doctor = null;
		doctor = doctorRepository.getById(id);
		return doctor;
	}

	public boolean deleteDoctors(Long id) {
		Doctors doctor = this.getDoctorUsingId(id);
		if (doctor == null) {
			return false;
		} else {
			appService.deleteDoctor(id);
			doctorRepository.deleteById(id);
			return true;
		}
	}

	public Doctors updateDoctors(Long id, DoctorDTO doctor) {
		Doctors doc = this.getDoctorUsingId(id);
		doc.setDoctorName(doctor.getDoctorName());
		doc.setExperience(doctor.getExperience());
		doc.setNoOfOperationsTaken(doctor.getNoOfOperationsTaken());
		doc.setOperationsSuccess(doctor.getOperationsSuccess());
		doc.setSuccessRatio(BigDecimal.valueOf((double) doctor.getOperationsSuccess() / doctor.getNoOfOperationsTaken())
				.setScale(2, RoundingMode.HALF_EVEN));
		return doctorRepository.save(doc);

	}

	public Specialization getSpecialization(String spec) {
		return service.getSpecializationByName(spec);
	}

}
