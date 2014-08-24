package com.twistlet.qir.admin.usermanagement.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CreateUserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CreateUserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
	}

}
