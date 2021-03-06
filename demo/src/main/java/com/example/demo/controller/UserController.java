package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.form.FileForm;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@ModelAttribute()
	public FileForm setupForm() {
		return new FileForm();
	}

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	private String init(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("loginuser", user.getName());

		return "fileoutput";

	}

	@RequestMapping(value = "/fileoutput", method = RequestMethod.POST)
	private String output(Model model, FileForm form) {
		//System.out.println(form.getFile());
		userService.output(form.getFile(), model);
		return "fileoutput";

	}
}
