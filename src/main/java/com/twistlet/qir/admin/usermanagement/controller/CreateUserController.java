package com.twistlet.qir.admin.usermanagement.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twistlet.qir.admin.usermanagement.service.UserManagementService;
import com.twistlet.qir.common.exception.DatabaseException;
import com.twistlet.qir.common.exception.FormValidationException;
import com.twistlet.qir.common.model.entity.User;

@Controller
public class CreateUserController {

	private UserManagementService userManagementService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public CreateUserController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CreateUserFormValidator());
	}

	@RequestMapping("/admin/user/insert")
	public String insert(CreateUserForm form, Errors errors) {
		if (errors.hasErrors()) {
			throw new FormValidationException();
		}
		try {
			User user = new User();
			user.setUsername(form.getUsername());
			user.setFullname(form.getFullname());
			user.setEnabled(form.getEnabled());
			user.setRoles(Collections.emptySet());
			userManagementService.create(user, form.getPassword());
			return "redirect:/admin/user/list";
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new DatabaseException();
		}
	}
}
