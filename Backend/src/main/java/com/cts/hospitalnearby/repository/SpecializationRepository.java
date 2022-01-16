package com.cts.hospitalnearby.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hospitalnearby.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	
	Specialization getBySplName(String spec);
}
