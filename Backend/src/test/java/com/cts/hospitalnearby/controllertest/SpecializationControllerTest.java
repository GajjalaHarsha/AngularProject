package com.cts.hospitalnearby.controllertest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.hospitalnearby.controller.SpecializationController;
import com.cts.hospitalnearby.dto.SpecializationDTO;
import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.service.SpecializationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SpecializationController.class)
@TestMethodOrder(OrderAnnotation.class)
class SpecializationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpecializationService specService;

	@Test
	@Order(1)
	@DisplayName("Get Specializations from the controller test")
	void getSpecializations() throws Exception {

		String URI = "/specialization/show";

		List<Specialization> sp = new ArrayList<>();
		List<SpecializationDTO> expSpec = new ArrayList<>();

		Specialization sp1 = new Specialization();
		sp1.setSplId(1L);
		sp1.setSplName("cardiology");

		Specialization sp2 = new Specialization();
		sp2.setSplId(2L);
		sp2.setSplName("gynacology");

		Specialization sp3 = new Specialization();
		sp3.setSplId(3L);
		sp3.setSplName("optimology");

		Specialization sp4 = new Specialization();
		sp4.setSplId(4L);
		sp4.setSplName("otalryngology");

		SpecializationDTO expsp1 = new SpecializationDTO();
		expsp1.setSplId(1L);
		expsp1.setSplName("Cardiology");

		SpecializationDTO expsp2 = new SpecializationDTO();
		expsp2.setSplId(2L);
		expsp2.setSplName("Gynacology");

		SpecializationDTO expsp3 = new SpecializationDTO();
		expsp3.setSplId(3L);
		expsp3.setSplName("Optimology");

		SpecializationDTO expsp4 = new SpecializationDTO();
		expsp4.setSplId(4L);
		expsp4.setSplName("Otalryngology");

		sp.add(sp1);
		sp.add(sp2);
		sp.add(sp3);
		sp.add(sp4);

		expSpec.add(expsp1);
		expSpec.add(expsp2);
		expSpec.add(expsp3);
		expSpec.add(expsp4);

		Mockito.when(specService.getAllSpecializations()).thenReturn(sp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(expSpec);

		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
