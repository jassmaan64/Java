package com.learn.controller;

import java.io.IOException;
//import java.sql.DriverManager;

import com.learn.bean.AccountBean;
import com.learn.service.AccountService;
import com.learn.util.AccountUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService = new AccountService();
    /**
     * Default constructor. 
     */
//    public LoginServlet() {
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String error = AccountUtil.validateRequest(request);
		
		if (error == null) {
			AccountBean accountBean = new AccountBean();
			accountBean.setUsername(request.getParameter("username"));
			accountBean.setPassword(request.getParameter("password"));
			
			error = accountService.authenticateAndPopulateAccountBean(accountBean);
			
			if(error == null) {
				HttpSession session = request.getSession();
				session.setAttribute("accountBean", accountBean);
				request.getRequestDispatcher("success.jsp").forward(request, response);
				
			}
			
			
		}
		else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		//1. Validate user request
		
		//2. call service and get email
	}

}
