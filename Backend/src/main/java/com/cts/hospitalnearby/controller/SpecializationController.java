package com.cts.hospitalnearby.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospitalnearby.dto.SpecializationDTO;
import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.service.SpecializationService;

@RestController()
public class SpecializationController {

	@Autowired
	private SpecializationService service;

	private static final Logger LOGGER = LogManager.getLogger(SpecializationController.class);

	@PostMapping("/specialization/add")
	public SpecializationDTO addSpecialization(@RequestBody SpecializationDTO specialization) {
		LOGGER.info("Inside add Specialization method in Specialization Controller");
		Specialization sp = new Specialization();
		sp.setSplName(specialization.getSplName());
		Specialization spec = service.addSpecilizationIntoDB(sp);
		SpecializationDTO savedspecialization = new SpecializationDTO();
		savedspecialization.setSplId(spec.getSplId());
		savedspecialization.setSplName(
				spec.getSplName().substring(0, 1).toUpperCase() + spec.getSplName().substring(1).toLowerCase());
		return savedspecialization;

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/specialization/show")
	public List<SpecializationDTO> getSpecializations() {
		LOGGER.info("Inside get all specializations method in Specialization Controller");
		List<Specialization> allSpecializations = service.getAllSpecializations();
		List<SpecializationDTO> specailzation = new ArrayList<>();
		for (Specialization spec : allSpecializations) {
			SpecializationDTO sp = new SpecializationDTO();
			sp.setSplId(spec.getSplId());
			sp.setSplName(
					spec.getSplName().substring(0, 1).toUpperCase() + spec.getSplName().substring(1).toLowerCase());
			specailzation.add(sp);
		}
		return specailzation;
	}
}
