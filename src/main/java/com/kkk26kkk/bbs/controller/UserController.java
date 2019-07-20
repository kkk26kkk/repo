package com.kkk26kkk.bbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kkk26kkk.bbs.model.UserVO;
import com.kkk26kkk.bbs.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/login")
	String login() {
		return "/user/user_login";
	}
	
	@RequestMapping("/user/logout")
	String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:../board";
	}
	
	@RequestMapping(value = "/user/login_ok", method = RequestMethod.POST)
	String loginOk(UserVO user, HttpServletRequest request) {
		String userPw = userService.getUserPw(user.getId());
		
		if(userPw.equals(user.getPw())) {
			request.getSession().setAttribute("userId", user.getId());
		}
		
		return "redirect:../board";
	}
	
}
