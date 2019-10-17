package com.kkk26kkk.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.common.code.Path;
import com.kkk26kkk.user.model.User;
import com.kkk26kkk.user.model.UserDto;
import com.kkk26kkk.user.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/join")
	String signUpForm() {
		return "/user/join";
	}
	
	@RequestMapping("/user/login")
	String loginForm() {
		return "/user/login";
	}
	
	@RequestMapping("/user/signUp")
	@ResponseBody Map<String, Object> signUp(@RequestBody UserDto userDto) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			userService.insertUser(userDto);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.Articles.getPath());
		} catch(Exception e) {
			result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody Map<String, Object> login(HttpSession session, @RequestBody UserDto userDto) {
		Map<String, Object> result = new HashMap<>();
		
		User user = userService.getUser(userDto.getUserId());
		
		try {
			if(StringUtils.equals(User.hash(userDto.getUserPw()), user.getUserPw())) {
				List<String> followeeIdList = userService.getFolloweeIds(user.getUserId());
				user.setFolloweeIds(StringUtils.join(followeeIdList, ","));
				
				session.setAttribute("user", user);
				result.put("code", HttpStatus.OK);
				result.put("redirect", Path.Articles.getPath());
			} else {
				result.put("code", HttpStatus.NOT_FOUND);
				result.put("msg", "해당 유저가 존재하지 않습니다.");
			}
		} catch(Exception e) {
			result.put("code", HttpStatus.NOT_FOUND);
			result.put("msg", "해당 유저가 존재하지 않습니다.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("/user/logout")
	@ResponseBody
	Map<String, Object> logout(HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		session.invalidate();
		
		result.put("code", HttpStatus.OK);
		result.put("redirect", Path.Articles.getPath());
		
		return result;
	}
	
	@RequestMapping("/user/follow/{userId}")
	@ResponseBody Map<String, Object> follow(User user, @PathVariable String userId) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			userService.insertUserFollow(userId, user.getUserId());
			result.put("code", HttpStatus.OK);
		} catch(Exception e) {
			result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			result.put("msg", e.getMessage());
		}
		
		return result;
	}
}