package com.twistlet.qir.common.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.twistlet.qir.common.model.entity.Role;
import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.RoleRepository;
import com.twistlet.qir.common.model.repository.UserRepository;

public class ElasticsearchDataInitService implements DataInitService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired
	public ElasticsearchDataInitService(final UserRepository userRepository,
			final RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void init() {
		initUsers();
		initRoles();
	}

	private void initRoles() {
		roleRepository.deleteAll();
		roleRepository.save(createAllRoles());

	}

	private List<Role> createAllRoles() {
		final List<Role> list = new ArrayList<Role>();
		list.add(createRole("ROLE_ADMIN"));
		return list;
	}

	private Role createRole(final String rolename) {
		final Role role = new Role();
		role.setRolename(rolename);
		return role;
	}

	private void initUsers() {
		userRepository.deleteAll();
		initAdmin();
		initNormalUsers();
	}

	private void initAdmin() {
		final User user = new User();
		user.setFullname("Administrator");
		user.setPassword("$2a$10$FPXLVweqprpxU9yGoX2RfuF/xtUVamB4XQByESlaqygK8d4cORgtq");
		user.setRoles(new LinkedHashSet<String>(Arrays.asList("ROLE_ADMIN")));
		user.setUsername("admin");
		userRepository.save(user);
	}

	private void initNormalUsers() {
		final String[] name = { "user01", "user02", "user03", "user04",
				"user05", "user06", "user07", "user08", "user09", "user10" };
		final String[] hashed = {
				"$2a$10$DWBqLX6nv6h2UTzg1FcX4uC21PN.MdMKWegsj/tLJvR/iDjzK/jpS",
				"$2a$10$xI8tXn1xzqe.YyeysYL5kuD3WEZLCom7vZ5TJOEQggoFBG.Z4jXhu",
				"$2a$10$CINTGP9y3EH.DdsgHb4BsuiX/cekGwK0A6If5xcZgtamzGt3ZQ06W",
				"$2a$10$bfUBwRcPUJJaibXJUekOWubfdUzAQyv91m/JcJ9ftu2Ryp/7kTVtW",
				"$2a$10$tc2P1gncfAJsKcA.hMRszuN3N1vljXaHaV4nhuHGekcnagEcLxDfu",
				"$2a$10$EQPjEsFVBAIPQCemXtWSwe2OVQt28mUp1jQahuQHep2CUOej0VxjC",
				"$2a$10$88Nu.zsouhH9YZG5kX7td.bm3j6fySQUSeJ7pAo5.ZifT88kTzUvO",
				"$2a$10$pIcoFjNK7iAgpogzOMQteO1wwTTYaXu5iajgFIS7c2JkaxJ4ugp0a",
				"$2a$10$vO4e/TobzfF0tabk2dZ.TewfShZHgIIAhekiHdbLmbnQ5g4MVTFiO",
				"$2a$10$kDuuZOFYrKNpKOcu/MOT8eOGecp..dlRo8m.LrJF4hga.6KKp1ula" };
		for (int id = 1; id <= 10; id++) {
			final User user = new User();
			user.setFullname("Normal user #" + id);
			user.setUsername(name[id - 1]);
			user.setPassword(hashed[id - 1]);
			user.setRoles(new LinkedHashSet<String>(Arrays.asList("ROLE_USER")));
			userRepository.save(user);
		}
	}

}
