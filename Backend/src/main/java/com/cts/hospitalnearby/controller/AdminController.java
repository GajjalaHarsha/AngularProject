package com.cts.hospitalnearby.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospitalnearby.entity.Admin;

@RestController
public class AdminController {

	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/admin/login")
	public Admin adminLogin(@RequestBody Admin adminLogin) {
		LOGGER.info("Inside the admin login method in Admin Controller");
		if (adminLogin.getName().equals("admin") && adminLogin.getPassword().equals("admin")) {
			return adminLogin;
		} else {
			return null;
		}
	}
}
