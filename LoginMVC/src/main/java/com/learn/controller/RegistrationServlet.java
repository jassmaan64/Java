package com.learn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import com.learn.bean.AccountBean;
import com.learn.service.AccountService;
import com.learn.util.AccountUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class RegistrationServlet
 */
//@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService = new AccountService();
    /**
     * Default constructor. 
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String error = AccountUtil.validateRegistrationRequest(request);
		
		if (error != null) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
		
		AccountBean accountBean = new AccountBean();
		accountBean.setUsername(request.getParameter("username"));
		accountBean.setPassword(request.getParameter("password"));
		accountBean.setEmail(request.getParameter("email"));
		accountBean.setStatus(request.getParameter("status"));
		
		error = accountService.registerAccount(accountBean);
		
		if(error != null) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

}
