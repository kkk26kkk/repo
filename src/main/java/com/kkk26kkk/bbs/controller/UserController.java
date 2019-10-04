package com.kkk26kkk.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.model.UserDto;
import com.kkk26kkk.bbs.service.UserFollowService;
import com.kkk26kkk.bbs.service.UserService;
import com.kkk26kkk.common.model.Path;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserFollowService userFollowService;
	
	@RequestMapping("/user/signUpForm")
	String signUpForm() {
		return "/user/signUpForm";
	}
	
	@RequestMapping("/user/loginForm")
	String loginForm() {
		return "/user/loginForm";
	}
	
	@RequestMapping("/user/signUp")
	@ResponseBody
	Map<String, Object> signUp(@RequestBody UserDto userDto) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			userService.insertUser(userDto);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.ArticleList.getPath());
		} catch(Exception e) {
			result.put("msg", e.getMessage());
			result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	Map<String, Object> loginOk(@RequestBody UserDto userDto, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		
		User user = userService.getUser(userDto.getUserId());
		
		try {
			if(User.hash(userDto.getUserPw()).equals(user.getUserPw())) {
				List<String> followeeIdList = userFollowService.getFolloweeIds(user.getUserId());
				user.setFolloweeIds(StringUtils.join(followeeIdList, ","));
				
				request.getSession().setAttribute("user", user);
				result.put("code", HttpStatus.OK);
				result.put("redirect", Path.ArticleList.getPath());
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
	
	@RequestMapping("/user/logout")
	@ResponseBody
	Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		
		request.getSession().invalidate();
		
		result.put("code", HttpStatus.OK);
		result.put("redirect", Path.ArticleList.getPath());
		
		return result;
	}
	
	@RequestMapping("/user/follow/{userId}")
	@ResponseBody Map<String, Object> follow(User user, @PathVariable String userId) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			userFollowService.insertUserFollow(userId, user.getUserId());
			result.put("code", HttpStatus.OK);
		} catch(Exception e) {
			result.put("msg", e.getMessage());
			result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
}