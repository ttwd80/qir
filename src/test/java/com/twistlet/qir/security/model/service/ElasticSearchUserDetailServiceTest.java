package com.twistlet.qir.security.model.service;

import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

public class ElasticSearchUserDetailServiceTest {

	private ElasticSearchUserDetailService sut;

	@Mock
	private UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new ElasticSearchUserDetailService(userRepository);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsernameNotFound() {
		when(userRepository.findOne("not_found")).thenReturn(null);
		sut.loadUserByUsername("not_found");
	}

	@Test
	public void testLoadUserByUsernameNoRoles() {
		final User userFromDb = new User();
		userFromDb.setPassword("#hashed#");
		userFromDb.setUsername("user-with-no-roles");
		userFromDb.setRoles(new LinkedHashSet<String>());
		when(userRepository.findOne("user-with-no-roles")).thenReturn(
				userFromDb);
		final UserDetails userDetails = sut
				.loadUserByUsername("user-with-no-roles");
		assertThat(userDetails, notNullValue());
		assertThat(userDetails.getAuthorities().size(), equalTo(0));
		assertThat(userDetails.getPassword(), equalTo("#hashed#"));
		assertThat(userDetails.getUsername(), equalTo("user-with-no-roles"));
		assertThat(userDetails.isAccountNonExpired(), equalTo(Boolean.TRUE));
		assertThat(userDetails.isAccountNonLocked(), equalTo(Boolean.TRUE));
		assertThat(userDetails.isCredentialsNonExpired(), equalTo(Boolean.TRUE));
		assertThat(userDetails.isEnabled(), equalTo(Boolean.TRUE));
	}

	@Test
	public void testLoadUserByUsernameWithRoles() {
		final User userFromDb = new User();
		userFromDb.setPassword("#hashed#");
		userFromDb.setUsername("user-with-roles");
		userFromDb.setRoles(new LinkedHashSet<String>(Arrays
				.asList("ROLE_USER")));
		when(userRepository.findOne("user-with-roles")).thenReturn(userFromDb);
		final UserDetails userDetails = sut
				.loadUserByUsername("user-with-roles");
		assertThat(userDetails, notNullValue());
		assertThat(userDetails.getAuthorities().size(), equalTo(1));
		assertThat(
				new ArrayList<GrantedAuthority>(userDetails.getAuthorities()),
				equalTo(new ArrayList<GrantedAuthority>(Arrays
						.asList(new SimpleGrantedAuthority("ROLE_USER")))));
		assertThat(userDetails.getPassword(), equalTo("#hashed#"));
		assertThat(userDetails.getUsername(), equalTo("user-with-roles"));
		assertThat(userDetails.isAccountNonExpired(), equalTo(Boolean.TRUE));
		assertThat(userDetails.isAccountNonLocked(), equalTo(Boolean.TRUE));
		assertThat(userDetails.isCredentialsNonExpired(), equalTo(Boolean.TRUE));
		assertThat(userDetails.isEnabled(), equalTo(Boolean.TRUE));
	}
}
