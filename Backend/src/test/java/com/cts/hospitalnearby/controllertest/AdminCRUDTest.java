package com.cts.hospitalnearby.controllertest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.hospitalnearby.controller.DoctorController;
import com.cts.hospitalnearby.dto.DoctorDTO;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.service.DoctorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DoctorController.class)
@TestMethodOrder(OrderAnnotation.class)
class AdminCRUDTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService docService;

	@Test
	@Order(1)
	@DisplayName("Create Doctor Admin test from controller")
	void createDoctor() throws Exception {

		DoctorDTO doctor = new DoctorDTO();
		doctor.setDoctorName("hazi");
		doctor.setExperience(5);
		doctor.setNoOfOperationsTaken(30);
		doctor.setOperationsSuccess(25);

		Doctors doc = new Doctors();
		Specialization sp = new Specialization();
		sp.setSplId(1L);
		sp.setSplName("cardiology");
		doc.setDoctorId(5L);
		doc.setExperience(doctor.getExperience());
		doc.setDoctorName(doctor.getDoctorName());
		doc.setNoOfOperationsTaken(doctor.getNoOfOperationsTaken());
		doc.setOperationsSuccess(doctor.getOperationsSuccess());
		doc.setSpec(sp);
		doc.setSuccessRatio(BigDecimal.valueOf((double) doctor.getOperationsSuccess() / doctor.getNoOfOperationsTaken())
				.setScale(2, RoundingMode.HALF_EVEN));

		doctor.setSplName("Cardiology");
		doctor.setSuccessRatio(doc.getSuccessRatio());
		doctor.setDoctorId(doc.getDoctorId());

		String URI = "/doctor/add/cardiology";
		String inputInJson = this.mapToJson(doctor);

		Mockito.when(docService.insertDoctorsIntoDB(Mockito.anyString(), Mockito.any(DoctorDTO.class))).thenReturn(doc);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);

	}

	@Test
	@Order(2)
	@DisplayName("Update doctor admin test from controller")
	void updateDoctor() throws Exception {
		DoctorDTO doctor = new DoctorDTO();
		doctor.setDoctorName("hazi");
		doctor.setExperience(5);
		doctor.setNoOfOperationsTaken(30);
		doctor.setOperationsSuccess(22);

		Doctors doc = new Doctors();
		Specialization sp = new Specialization();
		sp.setSplId(1L);
		sp.setSplName("cardiology");
		doc.setDoctorId(5L);
		doc.setExperience(doctor.getExperience());
		doc.setDoctorName(doctor.getDoctorName());
		doc.setNoOfOperationsTaken(doctor.getNoOfOperationsTaken());
		doc.setOperationsSuccess(doctor.getOperationsSuccess());
		doc.setSpec(sp);
		doc.setSuccessRatio(BigDecimal.valueOf((double) doctor.getOperationsSuccess() / doctor.getNoOfOperationsTaken())
				.setScale(2, RoundingMode.HALF_EVEN));

		doctor.setSplName("Cardiology");
		doctor.setSuccessRatio(doc.getSuccessRatio());
		doctor.setDoctorId(doc.getDoctorId());

		String URI = "/doctor/update/5";
		String inputInJson = this.mapToJson(doctor);

		Mockito.when(docService.updateDoctors(Mockito.anyLong(), Mockito.any(DoctorDTO.class))).thenReturn(doc);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
	}

	@Test
	@Order(3)
	@DisplayName("Delete Doctor admin test from controller")
	void deleteDoctor() throws Exception {

		String URI = "/doctor/delete/5";

		Mockito.when(docService.deleteDoctors(Mockito.anyLong())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo("deleted");

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
