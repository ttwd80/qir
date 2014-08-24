package com.twistlet.qir.security.model.service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

@Service("elasticSearchUserDetailService")
public class ElasticSearchUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public ElasticSearchUserDetailService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		final User user = userRepository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			final SimpleUserDetails userDetails = new SimpleUserDetails();
			userDetails.setUsername(user.getUsername());
			userDetails.setPassword(user.getPassword());
			userDetails.setAuthorities(toAuthorities(user.getRoles()));
			userDetails.setAccountNonExpired(true);
			userDetails.setAccountNonLocked(true);
			userDetails.setCredentialsNonExpired(true);
			userDetails.setEnabled(Boolean.TRUE.equals(user.getEnabled()));
			return userDetails;
		}
	}

	private Collection<? extends GrantedAuthority> toAuthorities(
			final Set<String> roles) {
		final Set<GrantedAuthority> set = new LinkedHashSet<GrantedAuthority>();
		for (final String role : roles) {
			set.add(new SimpleGrantedAuthority(role));
		}
		return set;
	}
}
