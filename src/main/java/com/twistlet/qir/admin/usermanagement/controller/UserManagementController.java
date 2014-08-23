package com.twistlet.qir.admin.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.qir.admin.usermanagement.service.UserManagementService;
import com.twistlet.qir.common.model.entity.User;

@Controller
public class UserManagementController {

	private UserManagementService userManagementService;

	@Autowired
	public UserManagementController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@RequestMapping("/admin/usermanagement/list")
	public ModelAndView list() {
		Iterable<User> list = userManagementService.listUser();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		return mav;
	}
}
