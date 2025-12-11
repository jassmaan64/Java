package com.learn.controller;

import java.lang.invoke.StringConcatFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learn.bean.AccountBean;
import com.learn.service.AccountService;
import com.learn.util.AccountUtil;
//import com.sun.jmx.remote.security.HashedPasswordManager;
//import com.sun.org.apache.bcel.internal.generic.NEW;


import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value={"", "/"}, method = RequestMethod.GET)
	public ModelAndView showRegistrationPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Registration");
		modelAndView.addObject("accountBean", new AccountBean());
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView handleRegistration(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("status") String status) {
		ModelAndView modelAndView = new ModelAndView();
		
		String error = AccountUtil.validateRegistrationRequest(username, password, email, status);
		
		if (error != null) {
			modelAndView.addObject("error", error);
			return modelAndView;
		}
		
		AccountBean accountBean = new AccountBean();
		accountBean.setUsername(username);
		accountBean.setPassword(password);
		accountBean.setEmail(email);
		accountBean.setStatus(status);
		
		error = accountService.registerAccount(accountBean);
		
		if(error != null) {
			modelAndView.addObject("error", error);
			return modelAndView;
		}
		else {
			modelAndView.setViewName("login");
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
		return modelAndView;
    }
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView handleLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String error = AccountUtil.validateRequest(username, password);
		
		if (error == null) {
			AccountBean accountBean = new AccountBean();
			accountBean.setUsername(username);
			accountBean.setPassword(password);
			
			error = accountService.authenticateAndPopulateAccountBean(accountBean);
			
			if(error == null) {
				modelAndView.addObject("accountBean", accountBean);
//				request.getRequestDispatcher("success.jsp").forward(request, response);
				modelAndView.setViewName("success");
				return modelAndView;
				
			}
			modelAndView.addObject("error", error);
			return modelAndView;
			
		}
		else {
			modelAndView.addObject("error", error);
			return modelAndView;
		}
		
	}
}
