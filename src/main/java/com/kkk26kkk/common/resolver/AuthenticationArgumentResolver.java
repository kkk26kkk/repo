package com.kkk26kkk.common.resolver;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kkk26kkk.bbs.model.User;

public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return User.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(null == user) {
			user = new User();
			user.setUserId(StringUtils.EMPTY);
			user.setUserPw(StringUtils.EMPTY);
			user.setUserName("¼Õ´Ô");
		}
		
		return user;
	}

}
