package com.lz.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lz.entity.Log;
import com.lz.entity.User;
import com.lz.service.LogService;
import com.lz.service.UserService;
import com.lz.util.MyUtils;

@Controller
public class HelloController {
	@Value("${userName}")
	private String name;

	@Value("${bookTitle}") // 从properties中取值
	private String title;

	@Resource
	private UserService userService;

	@Resource
	private LogService logService;

	@Autowired
	private HttpServletRequest request;

	
	
	@RequestMapping("/")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("name1", name);// name是上边定义的string变量，name1
										// 传给界面用${name1}获取
		map.addAttribute("bookTitle1", title);
		// return模板文件的名称，对应src/main/resources/templates/welcome.html
		return "welcome";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "register";
	}

	@RequestMapping("/toregister")
	public String register(Model model) {
		model.addAttribute("user", new User(null, null));//往界面传一个user对象
		return "register";
	}
	
	@RequestMapping("/tologin")
	public String login(Model model) {
		model.addAttribute("user", new User(null, null));//往界面传一个user对象
		return "login";
	}

	@RequestMapping("/insert")
	public String testInsert() {
		User user = new User(null, null);
		user.setAge("1");
		user.setName("1");
		userService.insertSelective(user);

		// logService.insertSelective(new
		// Log(MyUtils.getClientIpAddress(request)+":插入"+user.getName()+"账号"));
		return "welcome";
	}

}
