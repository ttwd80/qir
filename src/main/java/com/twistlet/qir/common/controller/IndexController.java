package com.twistlet.qir.common.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	private final Map<String, String> map;

	@Autowired
	public IndexController(
			@Value("#{roleLocationMap}") final Map<String, String> map) {
		super();
		this.map = map;
	}

	@RequestMapping("/index")
	public ModelAndView get(final NativeWebRequest request) {
		final Set<String> roles = map.keySet();
		for (final String role : roles) {
			if (request.isUserInRole(role)) {
				return new ModelAndView(map.get(role));
			}
		}
		return null;
	}
}
