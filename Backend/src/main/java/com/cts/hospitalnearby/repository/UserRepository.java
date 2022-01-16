package com.cts.hospitalnearby.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hospitalnearby.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User getByName(String name);

	

}
