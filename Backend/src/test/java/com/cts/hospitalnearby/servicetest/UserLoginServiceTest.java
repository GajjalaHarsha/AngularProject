package com.cts.hospitalnearby.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.hospitalnearby.dto.UserDTO;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
class UserLoginServiceTest {

	@Autowired
	private UserService service;

	@Test
	@Order(1)
	@DisplayName(value = "Login for existing user")
	void loginofExistingUser() {
		UserDTO user = new UserDTO();
		user.setName("Harsha");
		user.setEmail("gvsshhrao1998@gmail.com");
		user.setPassword("harsha123");
		User user1 = new User();
		user1.setName("Harsha");
		user1.setEmail("gvsshhrao1998@gmail.com");
		user1.setPassword("harsha123");
		assertEquals(user1, service.isValidUser(user));
	}

	@Test
	@Order(2)
	@DisplayName(value = "Login of invalid credentials of user")
	void loginofInvalidCredentialsOfUser() {
		UserDTO user = new UserDTO();
		user.setName("Harsha");
		user.setEmail("gvsshhrao198@gmail.com");
		user.setPassword("harsha125");
		assertEquals(null, service.isValidUser(user));
	}
}
