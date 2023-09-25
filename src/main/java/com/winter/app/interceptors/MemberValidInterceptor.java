package com.winter.app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/*Member update 시 password valid제외*/

public class MemberValidInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		/* parameter를 setting할 수 없음 생성해서 추가 하는 메서드가 존재하지 않는다. */
		return true;
	}
}
