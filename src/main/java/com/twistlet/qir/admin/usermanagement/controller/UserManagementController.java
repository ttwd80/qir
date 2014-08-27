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

	private Logger logger = LoggerFactory.getLogger(getClass());
	private UserManagementService userManagementService;

	@Autowired
	public UserManagementController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@RequestMapping("/admin/user/list")
	public ModelAndView list() {
		Iterable<User> list = userManagementService.listUser();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/user/remove", method = RequestMethod.POST)
	public Map<String, String> remove(@RequestParam("id") String id) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		try {
			userManagementService.remove(id);
			map.put("status", "success");
			map.put("id", id);
			return map;
		} catch (RuntimeException e) {
			logger.error(e.toString());
			map.put("status", "error");
			map.put("message", e.toString());
		}
		return map;
	}

}
