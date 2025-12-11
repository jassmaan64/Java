package com.learn.util;

//import javax.xml.crypto.dsig.dom.DOMValidateContext;

import jakarta.servlet.http.HttpServletRequest;

public class AccountUtil {

	public static String validateRequest(String username, String password) {
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
		if(username.isBlank() || password.isBlank()) {
			return "username or password should not be blank";
		}
		return null;
	}
	
	public static String validateRegistrationRequest(String username, String password, String email, String status) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String email    = request.getParameter("email");
//		String status   = request.getParameter("status");
		
		if (username == null || username.isBlank()
                || password == null || password.isBlank()
                || email == null || email.isBlank() || status == null || status.isBlank()) {
            return "username, password, email, or status should not be blank";
        }

        return null;
	}
}
