package com.cts.hospitalnearby.service;

import java.util.List;

import com.cts.hospitalnearby.dto.UserDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.exception.UserNotFoundException;

public interface UserService {

	public boolean isExistInDB(UserDTO user);

	public User addUserDetailsInDB(User user);

	public User isValidUser(UserDTO user);

	public User getUser(Long id) throws UserNotFoundException;

	public List<Appointments> getAppointments(Long id) throws UserNotFoundException;

	public User getUsingName(String name);
}
