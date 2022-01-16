package com.cts.hospitalnearby.servicetest;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.hospitalnearby.dto.UserDTO;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.repository.UserRepository;
import com.cts.hospitalnearby.service.UserService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@RunWith(SpringRunner.class)
class UserRegistrationTest {
	@Autowired
	private UserService service;

	@Autowired
	private UserRepository repository;

	@Test
	@Order(1)
	@DisplayName(value = "Registration of the existing user")
	void registeredUser() {
		UserDTO user = new UserDTO();
		user.setName("harsha");
		user.setEmail("gvsshhrao1998@gmail.com");
		user.setPassword("harsha123");
		assertTrue(service.isExistInDB(user));
	}

	@Test
	@Order(2)
	@DisplayName(value = "Registration of the not existing user")
	void newUser() {

		UserDTO user = new UserDTO();
		user.setName("hari");
		user.setEmail("hari@gmail.com");
		user.setPassword("hari123");
		assertEquals(false, service.isExistInDB(user));
		User storeuser = new User();
		storeuser.setName(user.getName());
		storeuser.setPassword(user.getPassword());
		storeuser.setEmail(user.getEmail());
		assertEquals(storeuser, service.addUserDetailsInDB(storeuser));
		User byName = repository.getByName(user.getName());
		repository.deleteById(byName.getId());
	}
}
