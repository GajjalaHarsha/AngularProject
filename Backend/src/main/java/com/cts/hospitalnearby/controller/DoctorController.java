package com.cts.hospitalnearby.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospitalnearby.dto.DoctorDTO;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	DoctorService service;

	private static final Logger LOGGER = LogManager.getLogger(DoctorController.class);

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/doctor/add/{spec}")
	public DoctorDTO insertDoctors(@PathVariable("spec") String spec, @RequestBody DoctorDTO doctors) {
		LOGGER.info("Inside insert doctors method in Doctor Controller");
		Doctors doctor = service.insertDoctorsIntoDB(spec, doctors);
		return this.getDoctorDTO(doctor);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/doctor/get/{spec}")
	public List<DoctorDTO> getDoctorsList(@PathVariable("spec") String spec) {
		LOGGER.info("Inside get doctor list method of particular specialization Doctor Controller");
		List<Doctors> doctors = service.getDoctorsOfSpecialization(spec);
		List<DoctorDTO> doctorsList = new ArrayList<>();
		for (Doctors doctor : doctors) {
			DoctorDTO doc = this.getDoctorDTO(doctor);
			String specName = doctor.getSpec().getSplName();
			doc.setSplName(specName.substring(0, 1).toUpperCase() + specName.substring(1));
			doctorsList.add(doc);
		}
		return doctorsList;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/doctor/delete/{id}")
	public String deleteDoctor(@PathVariable("id") Long id) {
		LOGGER.info("Inside delete doctors method in Doctor Controller");

		if (service.deleteDoctors(id)) {
			return "deleted";
		}

		else {
			return "not deleted";
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/doctor/singledoctor/{id}")
	public DoctorDTO getDoctor(@PathVariable("id") Long id) {
		LOGGER.info("Inside view doctors method in Doctor Controller");
		Doctors doctorUsingId = service.getDoctorUsingId(id);
		return this.getDoctorDTO(doctorUsingId);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/doctor/update/{id}")
	public DoctorDTO updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorDTO doctor) {
		LOGGER.info("Inside update doctors method in Doctor Controller");
		Doctors updateDoctors = service.updateDoctors(id, doctor);
		return this.getDoctorDTO(updateDoctors);

	}

	public DoctorDTO getDoctorDTO(Doctors doctor) {
		DoctorDTO doc = new DoctorDTO();
		doc.setDoctorId(doctor.getDoctorId());
		doc.setDoctorName(doctor.getDoctorName());
		doc.setExperience(doctor.getExperience());
		doc.setNoOfOperationsTaken(doctor.getNoOfOperationsTaken());
		doc.setSplName(doctor.getSpec().getSplName().substring(0, 1).toUpperCase()
				+ doctor.getSpec().getSplName().substring(1).toLowerCase());
		doc.setOperationsSuccess(doctor.getOperationsSuccess());
		doc.setSuccessRatio(BigDecimal.valueOf((double) doctor.getOperationsSuccess() / doctor.getNoOfOperationsTaken())
				.setScale(2, RoundingMode.HALF_EVEN));
		return doc;
	}

}
