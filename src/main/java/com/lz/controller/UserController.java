package com.lz.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lz.entity.Log;
import com.lz.entity.User;
import com.lz.service.LogService;
import com.lz.service.UserService;
import com.lz.util.MyUtils;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private LogService logService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/registerSave", method = RequestMethod.POST)
	public String userInsert(User user, Model model) {
		int insertCount = userService.insertSelective(user);
		logService.insertSelective(new Log(MyUtils.getClientIpAddress(request) + ":插入" + user.getName() + "账号"));
		if (insertCount > 0) {
			model.addAttribute("message", "添加成功");
		}
		return "welcome";
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(User user, Model model) {
		User isuser = userService.selectByNamePwd(user);
		if (isuser != null) {
			logService.insertSelective(new Log(MyUtils.getClientIpAddress(request) + user.getName() + "账号,成功登录"));
			model.addAttribute("message", "登录成功");
		} else {
			model.addAttribute("message", "登录失败");
		}
		return "welcome";
	}
	@RequestMapping(value = "/isexist", method = RequestMethod.POST)
	public String isexist(User user, Model model) {
		User isuser = userService.selectByNamePwd(user);
		if (isuser != null) {
			logService.insertSelective(new Log(MyUtils.getClientIpAddress(request) + user.getName() + "账号,成功登录"));
			model.addAttribute("message", "登录成功");
		} else {
			model.addAttribute("message", "登录失败");
		}
		return "welcome";
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<User> userList = userService.selectAll();
		model.addAttribute("userList", userList);
		model.addAttribute("message", "登录成功");
		model.addAttribute("isList", "true");
		return "welcome";
	}

}
