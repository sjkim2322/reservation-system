package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import kr.or.seongjin.reservation.dto.ReservationUser;

public class ReservationUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ReservationUser.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		System.out.println("Argument Resolver Handling!");
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		ReservationUser user = (ReservationUser) request.getSession().getAttribute("user");
		return user;
	}

}
