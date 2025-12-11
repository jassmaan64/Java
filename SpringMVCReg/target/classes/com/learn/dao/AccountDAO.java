package com.learn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.learn.bean.AccountBean;

public class AccountDAO {
	
		
	public AccountBean getAccountBean(AccountBean accountBean) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
				try {
					PreparedStatement ps = connection.prepareStatement("SELECT email, status FROM account WHERE username = ? AND password = ?");
					ps.setString(1, accountBean.getUsername());
					ps.setString(2, accountBean.getPassword());
					ResultSet resultSet = ps.executeQuery();
					
					while(resultSet.next()) {
						accountBean.setEmail(resultSet.getString(1));
						accountBean.setStatus(resultSet.getString(2));
					}
					
				} 
			catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return accountBean;
			
	}
	private Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void addAccount(AccountBean accountBean) {
		
		Connection connection = getConnection();
		if(connection != null) {
			PreparedStatement pStatement;
			try {
				pStatement = connection.prepareStatement("INSERT INTO account(username, password, email, status) VALUES (?, ?, ?, ?)");
				pStatement.setString(1, accountBean.getUsername());
				pStatement.setString(2, accountBean.getPassword());
				pStatement.setString(3, accountBean.getEmail());
				pStatement.setString(4, accountBean.getStatus());
				
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean existsByUsernameOrPassword(AccountBean accountBean) {
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		if (connection == null) {
//			try {
//				this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
		
		
		
        Connection conn = getConnection();
        if (conn == null) {
        	try {
        		try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	PreparedStatement ps;
			try {
				ps = conn.prepareStatement("SELECT * FROM account WHERE username = ? AND password = ?");
				ps.setString(1, accountBean.getUsername());
		        ps.setString(2, accountBean.getPassword());

		        ResultSet rs = ps.executeQuery();
		        return rs.next(); // true if at least one row found
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
        }
        return false;
       
	}
    
}
