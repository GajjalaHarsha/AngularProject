package com.cts.hospitalnearby.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hospitalnearby.entity.Doctors;

public interface DoctorRepository extends JpaRepository<Doctors, Long> {

	public Doctors getByDoctorName(String name);
}
