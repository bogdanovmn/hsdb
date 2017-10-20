package com.github.bogdanovmn.hsdb.webapp.config.security;


import com.github.bogdanovmn.hsdb.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class HsdbSecurityService {
	private static final Logger LOG = LogManager.getLogger(HsdbSecurityService.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;


	public boolean isLogged() {
//		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		return this.getLoggedInUser() != null;
	}

	public User getLoggedInUser() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof HsdbUserDetails) {
			return ((HsdbUserDetails)userDetails).getUser();
		}

		return null;
	}

	public void login(String username, String password) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
			= new UsernamePasswordAuthenticationToken(
				userDetails,
				password,
				userDetails.getAuthorities()
		);

		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			LOG.info(String.format("Auto login %s successfully!", username));
		}
	}
}