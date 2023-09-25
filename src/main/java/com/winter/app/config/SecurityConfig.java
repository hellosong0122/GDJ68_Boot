package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring() //.으로 연결 method chaining
				.antMatchers("/css/**")
				.antMatchers("/img/**")
				.antMatchers("/js/**")
				.antMatchers("/vendor/**")
				;
	}
	
	@Bean //메서드를 호출하세요 configuration -> bean 
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors()
			.and()
			.csrf()
			.disable()
			.authorizeHttpRequests()
				.antMatchers("/notice/add").hasRole("ADMIN") //ROLE_ADMIN ROLE_제외
				.antMatchers("/manager/*").hasAnyRole("ADMIN", "MANAGER")		
				.antMatchers("/").permitAll()
				.and()
			//form 관련 설정
			.formLogin()
				.loginPage("/member/login") //내장된 로그인폼을 사용하지 않고, 개발자가 만든 폼을 사용
				.defaultSuccessUrl("/")
				.failureUrl("/member/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.and()
			.sessionManagement()
			
				
			;
		
		return httpSecurity.build();
	}
}
