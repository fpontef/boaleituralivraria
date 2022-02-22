package com.nandotech.boaleituralivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandotech.boaleituralivraria.entity.User;
import com.nandotech.boaleituralivraria.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	public User find(Long id) {

		return userRepository.findById(id).get();
	}

	public List<User> list() {
		return userRepository.findAll();
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public User update(User user) {
		
		return userRepository.save(user);
	}

}
