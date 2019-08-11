package com.kkk26kkk.bbs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.model.UserDto;
import com.kkk26kkk.bbs.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	String login() {
		return "/user/login";
	}
	
	@RequestMapping("/logout")
	String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/login_ok", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	Map<String, Object> loginOk(@RequestBody UserDto userDto, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		
		User user = userService.getUser(userDto.getUserId());
		
		try {
			if(userDto.getUserPw().equals(user.getHashPw())) { // TODO DB에 해싱된 값으로 적재해주도록 데이터 마이그레이션
//				request.getSession().setAttribute("userId", user.getUserId());
//				request.getSession().setAttribute("userName", user.getUserName());
				request.getSession().setAttribute("user", user);
				result.put("code", HttpStatus.OK);
				result.put("redirect", request.getContextPath() + "/board/list");
			} else {
				result.put("msg", "해당 유저가 존재하지 않습니다.");
	            result.put("code", HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			result.put("msg", "해당 유저가 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		
		return result;
	}
	
}
