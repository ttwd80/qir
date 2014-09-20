package com.twistlet.qir.admin.usermanagement.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.qir.admin.usermanagement.service.UserManagementService;
import com.twistlet.qir.common.model.entity.User;

@Controller
public class UserManagementController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final UserManagementService userManagementService;

	@Autowired
	public UserManagementController(
			final UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@RequestMapping("/admin/user/list")
	public ModelAndView list() {
		final Iterable<User> list = userManagementService.listUser();
		final ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/user/remove", method = RequestMethod.POST)
	public Map<String, String> remove(@RequestParam("id") final String id) {
		final Map<String, String> map = new LinkedHashMap<String, String>();
		try {
			userManagementService.remove(id);
			map.put("status", "success");
			map.put("id", id);
			return map;
		} catch (final RuntimeException e) {
			logger.error(e.toString());
			map.put("status", "error");
			map.put("message", e.toString());
		}
		return map;
	}

	@RequestMapping("/admin/user/password-change")
	public ModelAndView passwordChange(@RequestParam("id") final String id) {
		final ModelAndView mav = new ModelAndView();
		final User user = userManagementService.get(id);
		mav.addObject(user);
		return mav;
	}
}
