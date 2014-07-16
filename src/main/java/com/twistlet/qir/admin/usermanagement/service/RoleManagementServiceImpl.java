package com.twistlet.qir.admin.usermanagement.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.twistlet.qir.common.model.entity.Role;
import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.RoleRepository;
import com.twistlet.qir.common.model.repository.UserRepository;

public class RoleManagementServiceImpl implements RoleManagementService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;

	public RoleManagementServiceImpl(final RoleRepository roleRepository,
			final UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void create(final Role role) {
		roleRepository.save(role);
	}

	@Override
	public void update(final Role role) {
		roleRepository.save(role);
	}

	@Override
	public void remove(final String rolename) {
		roleRepository.delete(rolename);
	}

	@Override
	public void assign(final List<String> usernames, final String rolename) {
		for (final String username : usernames) {
			final User user = userRepository.findOne(username);
			Set<String> roles = user.getRoles();
			if (roles == null) {
				roles = new LinkedHashSet<String>();
			}
			roles.add(rolename);
			userRepository.save(user);
		}
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
