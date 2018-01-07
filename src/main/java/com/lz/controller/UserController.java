package com.lz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
			request.getSession().setAttribute("userSession", user);
		} else {
			model.addAttribute("message", "登录失败");
		}
		return "welcome";
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model) {
		User user = (User) request.getSession().getAttribute("userSession");
		request.getSession().removeAttribute("userSession");
		if (request.getSession().getAttribute("userSession") == null) {
			if (user ==null) {
				
				model.addAttribute("message", "退出成功");
			}else {
				
				model.addAttribute("message", user.getName()+"退出成功");
				logService.insertSelective(new Log(MyUtils.getClientIpAddress(request) + user.getName()+ "账号,退出"));
			}
		} else {
			model.addAttribute("message", user.getName()+"退出失败");
		}
		return "welcome";
	}
	@RequestMapping(value = "/isexist", method = RequestMethod.POST)
	@ResponseBody // 此注解不能省略 否则ajax无法接受返回值
	public Map<String, Object> isexist(String name) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> userList = userService.selectByName(name);
		if (userList.size()>0) {
			
			resultMap.put("result", "isexist");
		}else {
			
			resultMap.put("result", "notexist");
		}
		return resultMap;
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<User> userList = userService.selectAll();
		model.addAttribute("userList", userList);
		model.addAttribute("message", "登录成功");
		model.addAttribute("isList", "true");
		return "welcome";
	}
	@RequestMapping(value = "/updateUser")
	public String updateUser(String id,Model model) {
		User user = userService.selectByPrimaryKey(Integer.parseInt(id));
		model.addAttribute("userByid", user);
		return "register";
	}

}
