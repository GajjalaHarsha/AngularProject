package com.cts.hospitalnearby.controllertest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService docService;

	@Test
	@Order(1)
	void getDoctors() throws Exception {
		String spec = "cardiology";

		String URI = "/doctor/get/cardiology";

		List<Doctors> doc = new ArrayList<>();
		List<DoctorDTO> getdoc = new ArrayList<>();

		Doctors docList = new Doctors();
		Specialization sp = new Specialization();
		sp.setSplId(1L);
		sp.setSplName(spec);
		docList.setDoctorId(1L);
		docList.setDoctorName("hemanth");
		docList.setExperience(2);
		docList.setNoOfOperationsTaken(15);
		docList.setOperationsSuccess(15);
		docList.setSuccessRatio(
				BigDecimal.valueOf((double) docList.getOperationsSuccess() / docList.getNoOfOperationsTaken())
						.setScale(2, RoundingMode.HALF_EVEN));
		docList.setSpec(sp);
		doc.add(docList);

		DoctorDTO docs = new DoctorDTO();
		docs.setDoctorId(docList.getDoctorId());
		docs.setDoctorName(docList.getDoctorName());
		docs.setExperience(docList.getExperience());
		docs.setNoOfOperationsTaken(docList.getNoOfOperationsTaken());
		docs.setOperationsSuccess(docList.getOperationsSuccess());
		docs.setSuccessRatio(
				BigDecimal.valueOf((double) docList.getOperationsSuccess() / docList.getNoOfOperationsTaken())
						.setScale(2, RoundingMode.HALF_EVEN));
		docs.setSplName("Cardiology");
		getdoc.add(docs);

		Mockito.when(docService.getDoctorsOfSpecialization(Mockito.any(String.class))).thenReturn(doc);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(getdoc);

		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	@Order(2)
	void viewOneDoctor() throws Exception {
		String URI = "/doctor/singledoctor/1";
		Doctors doc = new Doctors();
		Specialization sp = new Specialization();
		sp.setSplId(1L);
		sp.setSplName("cardiology");
		doc.setDoctorId(1L);
		doc.setDoctorName("hemanth");
		doc.setExperience(2);
		doc.setNoOfOperationsTaken(15);
		doc.setOperationsSuccess(15);
		doc.setSuccessRatio(BigDecimal.valueOf((double) doc.getOperationsSuccess() / doc.getNoOfOperationsTaken())
				.setScale(2, RoundingMode.HALF_EVEN));
		doc.setSpec(sp);

		DoctorDTO docs = new DoctorDTO();
		docs.setDoctorId(doc.getDoctorId());
		docs.setDoctorName(doc.getDoctorName());
		docs.setExperience(doc.getExperience());
		docs.setNoOfOperationsTaken(doc.getNoOfOperationsTaken());
		docs.setOperationsSuccess(doc.getOperationsSuccess());
		docs.setSuccessRatio(BigDecimal.valueOf((double) doc.getOperationsSuccess() / doc.getNoOfOperationsTaken())
				.setScale(2, RoundingMode.HALF_EVEN));
		docs.setSplName("Cardiology");

		Mockito.when(docService.getDoctorUsingId(Mockito.anyLong())).thenReturn(doc);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(docs);

		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
