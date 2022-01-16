package com.cts.hospitalnearby.servicetest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.hospitalnearby.dto.DoctorDTO;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.repository.DoctorRepository;
import com.cts.hospitalnearby.service.DoctorService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
class AdminTest {
	@Autowired
	private DoctorService docService;

	@Autowired
	private DoctorRepository docRepository;

	@Test
	@Order(1)
	@DisplayName("Create doctor")
	void addDoctor() {
		DoctorDTO doctor = new DoctorDTO();
		doctor.setDoctorName("hazi");
		doctor.setExperience(5);
		doctor.setNoOfOperationsTaken(30);
		doctor.setOperationsSuccess(25);
		Doctors insertDoctorsIntoDB = docService.insertDoctorsIntoDB("cardiology", doctor);
		assertNotNull(insertDoctorsIntoDB);
	}

	@Test
	@Order(2)
	@DisplayName("Update doctor")
	void updateDoctor() {
		DoctorDTO doctor = new DoctorDTO();
		doctor.setDoctorName("hazi");
		doctor.setExperience(4);
		doctor.setNoOfOperationsTaken(30);
		doctor.setOperationsSuccess(22);
		Doctors byDoctorName = docRepository.getByDoctorName(doctor.getDoctorName());
		System.out.println(byDoctorName.getDoctorName());
		Doctors insertDoctorsIntoDB = docService.updateDoctors(byDoctorName.getDoctorId(), doctor);
		assertNotNull(insertDoctorsIntoDB);
	}

	@Test
	@Order(3)
	@DisplayName("Delete doctor")
	void deleteDoctor() {
		Doctors byDoctorName = docRepository.getByDoctorName("hazi");
		assertTrue(docService.deleteDoctors(byDoctorName.getDoctorId()));
	}

}
