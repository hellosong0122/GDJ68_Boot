package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecuritySuccessHandler handler;
	
	@Autowired
	private MemberService memberService;
	

	
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
				//.successHandler(handler)
				.defaultSuccessUrl("/")
				//.failureUrl("/member/login?message=LoginFail")
				.failureHandler(getFailHandler())
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
				//.logoutSuccessUrl("/")
				.addLogoutHandler(getLogoutAdd())
				.logoutSuccessHandler(getLogoutHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
			.rememberMe()
				.tokenValiditySeconds(60)
				.key("rememberKey")
				.userDetailsService(memberService)
				.authenticationSuccessHandler(handler)
				.and()				
			.sessionManagement()
			
				
			;
		
		return httpSecurity.build();
	}
	
	private SecurityLogoutHandler getLogoutHandler() {
		return new SecurityLogoutHandler();
	}
	
	
	private SecurityLogoutAdd getLogoutAdd() {
		return new SecurityLogoutAdd();
	}

	private SecurityFailHandler getFailHandler() {
		return new SecurityFailHandler();
	}
}
