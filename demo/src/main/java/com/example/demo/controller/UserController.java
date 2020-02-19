package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Test;
import com.example.demo.form.FileForm;
import com.example.demo.form.TestForm;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@ModelAttribute()
	public FileForm setupForm() {
		return new FileForm();
	}

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String init(Model model) throws IllegalAccessException, InvocationTargetException {
		//model.addAttribute("User", userService.findAll());
		TestForm form = new TestForm(1, 2, "password", "position", "text");

		ModelMapper modelMapper = new ModelMapper();
		Test test = modelMapper.map(form, Test.class);

		//BeanUtils.copyProperties(test, form);
		model.addAttribute("User", test);
		return "fileoutput";

	}

	@RequestMapping(value = "/fileoutput", method = RequestMethod.POST)
	private String output(Model model, FileForm form) {
		//System.out.println(form.getFile());
		userService.output(form.getFile(), model);
		return "fileoutput";

	}
}
