package com.winter.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecuritySuccessHandler implements AuthenticationSuccessHandler{
	//로그인성공했을때 로그인했을때 시간 -> db저장 -> 휴면계정
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("---- Authentication : {} -----", authentication);
		
		log.info("========PathInfo : {}=======", request.getPathInfo());
		
		log.info("========RequestURI : {}=======", request.getRequestURI());
		
		log.info("========RequestURL : {}=======", request.getRequestURL());
		
		response.sendRedirect("/");
		
	}

}
