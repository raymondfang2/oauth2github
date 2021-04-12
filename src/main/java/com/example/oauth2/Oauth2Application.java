package com.example.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
public class Oauth2Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				// allow anonymous access to the root page
				.antMatchers("/").permitAll()

				// all other requests
				.anyRequest().authenticated()

				// After we logout, redirect to root page,
				// by default Spring will send you to /login?logout
				.and().logout().logoutSuccessUrl("/")

				// enable OAuth2/OIDC
				.and().oauth2Login();
	}

}
