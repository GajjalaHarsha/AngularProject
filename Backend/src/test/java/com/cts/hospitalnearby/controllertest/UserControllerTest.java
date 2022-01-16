package com.cts.hospitalnearby.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

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

import com.cts.hospitalnearby.controller.UserController;
import com.cts.hospitalnearby.dto.UserDTO;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Order(1)
	@Test
	@DisplayName(value = "registration for controller test of the existing user")
	void register() throws Exception {

		UserDTO user = new UserDTO();
		user.setEmail("gvsshhrao1998@gmail.com");
		user.setName("harsha");
		user.setPassword("harsha123");

		String URI = "/user/register";
		String inputInJson = this.mapToJson(user);

		Mockito.when(userService.isExistInDB(Mockito.any(UserDTO.class))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertTrue(outputInJson.contentEquals(""));

	}

	@Test
	@Order(2)
	@DisplayName(value = "Registration for controller test of the non existing user")
	void invalidUser() throws Exception {

		UserDTO user = new UserDTO();
		user.setEmail("hari@gmail.com");
		user.setName("hari");
		user.setPassword("hari123");

		User storeduser = new User();
		storeduser.setId(2L);
		storeduser.setName(user.getName());
		storeduser.setEmail(user.getEmail());
		storeduser.setPassword(user.getPassword());

		user.setId(storeduser.getId());

		String URI = "/user/register";
		String inputInJson = this.mapToJson(user);

		Mockito.when(userService.isExistInDB(Mockito.any(UserDTO.class))).thenReturn(false);
		Mockito.when(userService.addUserDetailsInDB(Mockito.any(User.class))).thenReturn(storeduser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);

	}

	@Test
	@Order(3)
	@DisplayName(value = "Login for controller test of the non existing user")
	void notValidUser() throws Exception {
		UserDTO user = new UserDTO();
		user.setEmail("ahmed@gmail.com");
		user.setName("ahmed");
		user.setPassword("ahmed123");

		String URI = "/user/login";
		String inputInJson = this.mapToJson(user);

		Mockito.when(userService.isValidUser(Mockito.any(UserDTO.class))).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertTrue(outputInJson.contentEquals(""));
	}

	@Test
	@Order(4)
	@DisplayName(value = "Login for controller test of the non existing user")
	void validUser() throws Exception {
		UserDTO user = new UserDTO();
		user.setEmail("harsha@gmail.com");
		user.setName("harsha");
		user.setPassword("harsha123");

		User storeduser = new User();
		storeduser.setId(1L);
		storeduser.setName(user.getName());
		storeduser.setEmail(user.getEmail());
		storeduser.setPassword(user.getPassword());

		user.setId(storeduser.getId());

		String URI = "/user/login";
		String inputInJson = this.mapToJson(user);

		Mockito.when(userService.isValidUser(Mockito.any(UserDTO.class))).thenReturn(storeduser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
