package com.cts.hospitalnearby.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.service.SpecializationService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
class SpecializationServiceTest {

	@Autowired
	private SpecializationService splService;

	@Test
	@Order(1)
	@DisplayName(value = "listing all specializations")
	void getTheAllSpecializations() {
		String specialization[] = new String[4];
		int a[] = new int[4];
		specialization[0] = "cardiology";
		specialization[1] = "gynacology";
		specialization[2] = "optimology";
		specialization[3] = "otalryngology";
		System.out.println(specialization[1]);
		List<Specialization> allSpecializations = splService.getAllSpecializations();
		for (Specialization sp : allSpecializations) {
			for (int i = 0; i < 4; i++) {
				if (sp.getSplName().equalsIgnoreCase(specialization[i])) {
					a[i] += 1;
					break;
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			assertEquals(1, a[i]);
		}
	}
}
