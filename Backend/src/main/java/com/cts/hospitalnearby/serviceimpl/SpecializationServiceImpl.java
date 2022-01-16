package com.cts.hospitalnearby.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospitalnearby.entity.Specialization;
import com.cts.hospitalnearby.repository.SpecializationRepository;
import com.cts.hospitalnearby.service.SpecializationService;

@Service
public class SpecializationServiceImpl implements SpecializationService {

	@Autowired
	private SpecializationRepository specializationRepository;

	public Specialization addSpecilizationIntoDB(Specialization specialization) {

		return specializationRepository.save(specialization);
	}

	public List<Specialization> getAllSpecializations() {
		return specializationRepository.findAll();
	}

	public Specialization getSpecializationByName(String spec) {
		return specializationRepository.getBySplName(spec);
	}

}
