package com.cts.hospitalnearby.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.hospitalnearby.controller.AppointmentController;
import com.cts.hospitalnearby.dto.AppointmentDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.service.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppointmentController.class)
@TestMethodOrder(OrderAnnotation.class)
class AppointmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppointmentService appService;

	@Test
	@Order(1)
	@DisplayName("Not Booked Appointment Controller test")
	void notBookAppointment() throws Exception {

		Appointments app = new Appointments();
		app.setAppointmentDate(LocalDate.now().plusDays(5));
		app.setAppointmentTime(LocalTime.of(12, 30));

		String URI = "/appointments/user/1/doctor/1";
		String inputInJson = this.mapToJson(app);

		Mockito.when(
				appService.bookAppointment(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(AppointmentDTO.class)))
				.thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		System.out.println(outputInJson);
		assertTrue(outputInJson.contentEquals(""));
	}

	@Test
	@Order(2)
	@DisplayName("Book Appointment controller test")
	void bookAppointment() throws Exception {

		Appointments app = new Appointments();
		app.setAppointmentDate(LocalDate.now());
		app.setAppointmentTime(LocalTime.of(12, 30));
		Doctors docList = new Doctors();
		Specialization sp = new Specialization();
		sp.setSplId(1L);
		sp.setSplName("cardiology");
		docList.setDoctorId(1L);
		docList.setDoctorName("hemanth");
		docList.setExperience(2);
		docList.setNoOfOperationsTaken(15);
		docList.setOperationsSuccess(15);
		docList.setSuccessRatio(
				BigDecimal.valueOf((double) docList.getOperationsSuccess() / docList.getNoOfOperationsTaken())
						.setScale(2, RoundingMode.HALF_EVEN));
		docList.setSpec(sp);
		User storeduser = new User();
		storeduser.setId(2L);
		storeduser.setName("harsha");
		storeduser.setEmail("gvsshhrao1998@gmail.com");
		storeduser.setPassword("harsha123");
		app.setDoctors(docList);
		app.setUser(storeduser);
		app.setAppointmentId(1L);

		AppointmentDTO booked = new AppointmentDTO();
		booked.setUserName("harsha");
		booked.setDoctorName("hemanth");
		booked.setDepartment("cardiology");
		booked.setAppointmentDate(app.getAppointmentDate());
		booked.setAppointmentTime(app.getAppointmentTime());

		String URI = "/appointments/user/1/doctor/1";
		String inputInJson = this.mapToJson(booked);

		Mockito.when(
				appService.bookAppointment(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(AppointmentDTO.class)))
				.thenReturn(app);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(inputInJson).isEqualTo(outputInJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objectMapper.writeValueAsString(object);
	}
}
