package com.fball.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter  {
		
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/","/login","/check_login","/logout","/registy","/new_player","/new_club", "/error").permitAll()
					.antMatchers("/notifi","/profile", "/update_profile","/change_password").permitAll()
					.antMatchers("/list-club/**","/club/**", "/match/**", "/virtual-match/**","/my-match/**","/friend/**","/find-match/**","/home").hasAnyRole("player")
					.antMatchers("/manager-club/**","/manager-match/**").hasAnyRole("manager")
					.anyRequest().denyAll()
				.and()
				.formLogin()
					.loginPage("/login");
		
		http.exceptionHandling().accessDeniedPage("/error");
	}
		
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new CustomAuthenticationProvider());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web	.ignoring()
				.antMatchers("/static/**");
	}
	
}
