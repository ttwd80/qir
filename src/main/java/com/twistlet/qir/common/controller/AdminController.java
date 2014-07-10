package com.twistlet.qir.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.qir.common.model.service.AdminService;

@Controller
public class AdminController {

	private final AdminService adminService;

	@Autowired
	public AdminController(final AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping("/admin/index")
	public ModelAndView index() {
		final ModelAndView mav = new ModelAndView();
		mav.addObject("count", adminService.countRegisteredUsers());
		return mav;
	}
}
