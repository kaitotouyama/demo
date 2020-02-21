package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@EnableTransactionManagement
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		User user = userRepository.findUser(name);

		if (user == null) {
			throw new UsernameNotFoundException("User" + name + "was not found in the database");
		}

		return user;
	}

}