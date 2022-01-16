package com.cts.hospitalnearby.servicetest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.service.DoctorService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
class DoctorServiceTest {
	@Autowired
	private DoctorService docService;

	@Test
	@Order(1)
	@DisplayName(value = "getting doctors in all specializations")
	void getTheDoctorsInAllSpecializations() {
		String specialization[] = new String[4];
		specialization[0] = "cardiology";
		specialization[1] = "gynacology";
		specialization[2] = "optimology";
		specialization[3] = "otalryngology";
		for (int i = 0; i < 4; i++) {
			List<Doctors> doctorsOfSpecialization = docService.getDoctorsOfSpecialization(specialization[i]);
			assertTrue(doctorsOfSpecialization.size() > 0);
		}
	}

	@Test
	@Order(2)
	@DisplayName(value = "viewing the doctor in the specializations")
	void viewDoctor() {
		Doctors doctorUsingId = docService.getDoctorUsingId(1L);
		assertNotNull(doctorUsingId.getDoctorName());
	}
}
