package com.twistlet.qir.admin.usermanagement.service;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.twistlet.qir.common.model.entity.Role;
import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.RoleRepository;
import com.twistlet.qir.common.model.repository.UserRepository;

public class RoleManagementServiceImplTest {

	RoleManagementServiceImpl sut;

	@Mock
	RoleRepository roleRepository;

	@Mock
	UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new RoleManagementServiceImpl(roleRepository, userRepository);
	}

	@Test
	public void testCreate() {
		final ArgumentCaptor<Role> roleCaptor = ArgumentCaptor
				.forClass(Role.class);

		sut.create(createRole1());
		verify(roleRepository).save(roleCaptor.capture());
		final Role captured = roleCaptor.getValue();
		assertThat(captured.getRolename(), equalTo("ROLE_1"));
		assertThat(captured.getDescription(), equalTo("Role 1"));

	}

	@Test
	public void testUpdate() {
		final ArgumentCaptor<Role> roleCaptor = ArgumentCaptor
				.forClass(Role.class);
		final Role role1Update = createRole1();
		role1Update.setDescription("New Description");
		sut.update(role1Update);
		verify(roleRepository).save(roleCaptor.capture());
		final Role captured = roleCaptor.getValue();
		assertThat(captured.getRolename(), equalTo("ROLE_1"));
		assertThat(captured.getDescription(), equalTo("New Description"));
	}

	@Test
	public void testRemove() {
		sut.remove("ROLE_1");
		verify(roleRepository).delete("ROLE_1");
	}

	@Test
	public void testAssignOneEmpty() {
		final User user1 = new User();
		user1.setUsername("user1");
		user1.setRoles(new LinkedHashSet<String>());
		when(userRepository.findOne("user1")).thenReturn(user1);
		sut.assign(Arrays.asList("user1"), "ROLE_1");
		final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(captor.capture());
		final User captured = captor.getValue();
		assertThat(captured.getRoles().size(), equalTo(1));
		assertThat(captured.getRoles().iterator().next(), equalTo("ROLE_1"));
	}

	private Role createRole1() {
		final Role role = new Role();
		role.setRolename("ROLE_1");
		role.setDescription("Role 1");
		return role;
	}

}
