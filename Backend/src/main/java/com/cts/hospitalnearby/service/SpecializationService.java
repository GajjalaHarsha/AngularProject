package com.cts.hospitalnearby.service;

import java.util.List;

import com.cts.hospitalnearby.entity.Specialization;

public interface SpecializationService {

	public Specialization addSpecilizationIntoDB(Specialization specialization);

	public List<Specialization> getAllSpecializations();

	public Specialization getSpecializationByName(String spec);
}
