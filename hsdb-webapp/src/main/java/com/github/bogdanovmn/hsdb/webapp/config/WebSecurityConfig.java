package com.github.bogdanovmn.hsdb.webapp.config;

import com.github.bogdanovmn.hsdb.webapp.config.security.HsdbUserDetailsService;
import com.github.bogdanovmn.hsdb.webapp.config.security.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private HsdbUserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(new Md5PasswordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/registration").anonymous()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/admin/**").hasRole("User")
				.anyRequest().authenticated()

		.and().formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/collection/in", true)
			.permitAll()

		.and().logout()
			.logoutRequestMatcher(
				new AntPathRequestMatcher("/logout")
			)
			.logoutSuccessUrl("/login")
			.permitAll()

		.and().csrf()
			.disable();
	}
}
