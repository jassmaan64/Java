package com.learn;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import java.io.PrintWriter;

/**
 * Servlet implementation class RegistrationServlet
 */
//@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public RegistrationServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			
//			String connectionURLString = config.getServletContext().getInitParameter("connectionURL");
//			String usernameString = config.getServletContext().getInitParameter("username");
//			String password = config.getServletContext().getInitParameter("password");
//			
//			try {
//				this.connection = DriverManager.getConnection(connectionURLString, usernameString, password);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountUsername = request.getParameter("username");
		String accountPassword = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = (request.getParameter("firstName") + "@email.com");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null) {
				try {
					this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
					try {
						PreparedStatement ps = connection.prepareStatement("SELECT * FROM account WHERE username = ?");
						ps.setString(1, accountUsername);
//						ps.setString(2, accountPassword);
						ResultSet executeQuery = ps.executeQuery();
						
						boolean success = false;
						
						while(executeQuery.next())success = true;
						if(success) {
							out.println("<h3 style='color:red;'>Password already taken. Please choose another.</h3>");
			                RequestDispatcher rd = request.getRequestDispatcher("Registration.html");// error msg
			                rd.include(request, response);
						}
						else {
							ps = connection.prepareStatement("SELECT * FROM account WHERE password = ?");
							ps.setString(1, accountPassword);
							executeQuery = ps.executeQuery();
							
							while(executeQuery.next())success = true;
							if(success) {
								out.println("<h3 style='color:red;'>Password already taken. Please choose another.</h3>");
				                RequestDispatcher rd = request.getRequestDispatcher("Registration.html");// error msg
				                rd.include(request, response);
							}
							
							else {
								ps = connection.prepareStatement("INSERT INTO account(username, password, email) VALUES (?, ?, ?)");
								ps.setString(1, accountUsername);
								ps.setString(2, accountPassword);
								ps.setString(3, email);
//								ps.setString(3, firstName);
//								ps.setString(4, lastName);
								
								ps.executeUpdate();
								
								RequestDispatcher rd = request.getRequestDispatcher("login.html");
					            rd.forward(request, response);
								
//								response.sendRedirect("login.html");
							}
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
