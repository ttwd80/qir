package com.twistlet.qir.common.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.twistlet.qir.common.model.entity.Role;
import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.RoleRepository;
import com.twistlet.qir.common.model.repository.UserRepository;

public class ElasticsearchDataInitService implements DataInitService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public ElasticsearchDataInitService(PasswordEncoder passwordEncoder,
			final UserRepository userRepository,
			final RoleRepository roleRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void init() {
		if (roleRepository.count() == 0L) {
			initRoles();
		}
		if (userRepository.count() == 0L) {
			initUsers();
		}
	}

	private void initRoles() {
		roleRepository.save(createAllRoles());

	}

	private List<Role> createAllRoles() {
		final List<Role> list = new ArrayList<Role>();
		list.add(createRole("ROLE_ADMIN"));
		list.add(createRole("ROLE_USER"));
		return list;
	}

	private Role createRole(final String rolename) {
		final Role role = new Role();
		role.setRolename(rolename);
		return role;
	}

	private void initUsers() {
		initAdmin();
		initNormalUsers();
	}

	private void initAdmin() {
		final User user = new User();
		user.setFullname("Administrator");
		user.setRoles(new LinkedHashSet<String>(Arrays.asList("ROLE_ADMIN")));
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("cefew86traqe"));
		user.setEnabled(Boolean.TRUE);
		userRepository.save(user);
	}

	private void initNormalUsers() {
		final String[] name = { "user01", "user02", "user03", "user04",
				"user05", "user06", "user07", "user08", "user09", "user10" };
		final String[] hashed = { passwordEncoder.encode("wruy7cran5tr01"),
				passwordEncoder.encode("wruy7cran5tr02"),
				passwordEncoder.encode("wruy7cran5tr03"),
				passwordEncoder.encode("wruy7cran5tr04"),
				passwordEncoder.encode("wruy7cran5tr05"),
				passwordEncoder.encode("wruy7cran5tr06"),
				passwordEncoder.encode("wruy7cran5tr07"),
				passwordEncoder.encode("wruy7cran5tr08"),
				passwordEncoder.encode("wruy7cran5tr09"),
				passwordEncoder.encode("wruy7cran5tr10") };
		for (int id = 1; id <= 10; id++) {
			final User user = new User();
			user.setFullname("Normal user #" + id);
			user.setUsername(name[id - 1]);
			user.setPassword(hashed[id - 1]);
			user.setRoles(new LinkedHashSet<String>(Arrays.asList("ROLE_USER")));
			user.setEnabled(Boolean.TRUE);
			userRepository.save(user);
		}
	}

	@Override
	public void clear() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
	}

}
