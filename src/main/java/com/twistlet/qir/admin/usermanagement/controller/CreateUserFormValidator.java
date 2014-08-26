package com.twistlet.qir.admin.usermanagement.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CreateUserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CreateUserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CreateUserForm form = (CreateUserForm) target;
		if (StringUtils.isEmpty(form.getUsername())) {
			errors.reject(StringUtils.EMPTY);
		}
		if (StringUtils.isEmpty(form.getPassword())) {
			errors.reject(StringUtils.EMPTY);
		}
		if (!StringUtils.equals(form.getPassword(), form.getPasswordConfirm())) {
			errors.reject(StringUtils.EMPTY);
		}
		if (null == form.getEnabled()) {
			errors.reject(StringUtils.EMPTY);
		}
	}

}
