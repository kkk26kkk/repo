package com.kkk26kkk.bbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	String login() {
		return "/user/user_login";
	}
	
	@RequestMapping("/logout")
	String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:/board_list";
	}
	
	@RequestMapping(value = "/login_ok", method = RequestMethod.POST)
	String loginOk(User user, HttpServletRequest request) {
		String userPw = userService.getUserPw(user.getId());
		
		if(userPw.equals(user.getHashPw())) {
			request.getSession().setAttribute("userId", user.getId());
		}
		
		return "redirect:/board_list";
	}
	
}
