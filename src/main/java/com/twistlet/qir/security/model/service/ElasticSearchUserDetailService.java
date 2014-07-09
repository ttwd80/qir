package com.twistlet.qir.security.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("elasticSearchUserDetailService")
public class ElasticSearchUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
