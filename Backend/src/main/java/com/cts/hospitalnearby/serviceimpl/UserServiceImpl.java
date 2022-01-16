package com.cts.hospitalnearby.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospitalnearby.dto.UserDTO;
import com.cts.hospitalnearby.entity.Appointments;
import com.cts.hospitalnearby.entity.User;
import com.cts.hospitalnearby.exception.UserNotFoundException;
import com.cts.hospitalnearby.repository.UserRepository;
import com.cts.hospitalnearby.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public boolean isExistInDB(UserDTO user) {
		User byId = this.getUsingName(user.getName());
		return byId != null;
	}

	public User addUserDetailsInDB(User user) {
		return userRepository.save(user);
	}

	public User isValidUser(UserDTO userDTO) {
		User byId = this.getUsingName(userDTO.getName());
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		if (isExistInDB(userDTO) && byId.equals(user)) {
			return byId;
		} else {
			return null;
		}
	}

	public User getUser(Long id) throws UserNotFoundException {
		Optional<User> byId = userRepository.findById(id);
		if (!byId.isPresent()) {
			throw new UserNotFoundException("The user with id - " + id + " does not exists");
		}
		return userRepository.getById(id);

	}

	public List<Appointments> getAppointments(Long id) throws UserNotFoundException {
		User user = this.getUser(id);
		return user.getAppointments();

	}

	public User getUsingName(String name) {
		return userRepository.getByName(name);

	}
}
