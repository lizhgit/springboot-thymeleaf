package com.lz.aspect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.gson.Gson;
import com.lz.controller.HelloController;
import com.lz.entity.Log;
import com.lz.entity.User;
import com.lz.service.LogService;
import com.lz.util.MyUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
@Aspect // 定义一个切面
@Configuration
public class LogRecordAspect {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Resource
	private LogService logService;

	// 定义切点Pointcut
	@Pointcut("execution(* com.lz.controller.*Controller.*(..))")
	public void excudeService() {
	}

	@Around("excudeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		logger.info("请求开始+ 各个参数+ url: {}+ method: {}+ uri: {}+ params: {}" + url + method + uri + queryString);
		logService.insertSelective(
				new Log(MyUtils.getClientIpAddress(request) + ":请求开始+ 各个参数+ url:+ method: + uri: + params: 分别为" + url
						+ ";" + method + ";" + uri + ";" + queryString));
	
		User user = (User) request.getSession().getAttribute("userSession");
		if (uri.equals("/")||uri.equals("/toregister")||uri.equals("/isexist")||uri.equals("/registerSave")||uri.equals("/tologin")||uri.equals("/loginCheck")) {
			// result的值就是被拦截方法的返回值
			Object result = pjp.proceed();
			Gson gson = new Gson();
			logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
			return result;
		} else {
			if (user == null) {
				//model.addAttribute("message", "信息丢失，请登录");
				request.getSession().setAttribute("loseUser", "用户信息丢失，重新登陆");
				return "redirect:/";
			}else {
				// result的值就是被拦截方法的返回值
				Object result = pjp.proceed();
				 Gson gson = new Gson();
				 logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
				 
				 return result;
			}
		}
		
	}
}