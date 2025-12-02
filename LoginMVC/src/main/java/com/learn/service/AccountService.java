package com.learn.service;

import com.learn.dao.AccountDAO;
import com.learn.bean.AccountBean;

public class AccountService {
	
	AccountDAO accountDAO = new AccountDAO();
	
	public String authenticateAndPopulateAccountBean(AccountBean accountBean) {
		
		String error = null;
		accountBean = accountDAO.getAccountBean(accountBean);
		
		if(accountBean.getEmail() == null) {
			error = "Invalid username or password";
			
		}
		
		return error;
	}

	public String registerAccount(AccountBean accountBean) {
		boolean exists = accountDAO.existsByUsernameOrPassword(accountBean);
		if(exists)return "Username or password already exists";
		accountDAO.addAccount(accountBean);
		
		return null;
	}
}
