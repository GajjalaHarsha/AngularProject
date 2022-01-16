package com.cts.hospitalnearby.service;

import java.util.List;

import com.cts.hospitalnearby.dto.DoctorDTO;
import com.cts.hospitalnearby.entity.Doctors;
import com.cts.hospitalnearby.entity.Specialization;

public interface DoctorService {

	public Doctors insertDoctorsIntoDB(String spec, DoctorDTO doctors);

	public List<Doctors> getDoctorsOfSpecialization(String spec);

	public Doctors getDoctorUsingId(Long id);

	public boolean deleteDoctors(Long id);

	public Doctors updateDoctors(Long id, DoctorDTO doctor);

	public Specialization getSpecialization(String spec);
}
