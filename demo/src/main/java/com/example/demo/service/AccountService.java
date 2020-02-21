package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;

@Service
@EnableTransactionManagement
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void create(String name, String password) {

		User user = new User();
		user.setName(name);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole("ROLE_USER");

		accountRepository.save(user);
	}
}
