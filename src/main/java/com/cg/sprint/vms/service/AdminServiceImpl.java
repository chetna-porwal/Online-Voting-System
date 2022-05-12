package com.cg.sprint.vms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sprint.vms.dao.AdminLoginDao;
import com.cg.sprint.vms.dao.CandidateDao;

/**
 * @author Sameeksha
 * Date:- 16/02/2021
 * description:- AdminServiceImpl is a service layer class which implements AdminService interface.
 *               In this class we are defining different methods to perform operation on Admin entity.
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminService 
{
	@Autowired
	AdminLoginDao dao;
	
	@Autowired
	CandidateDao daocd;
		
	/**
	 * @author Sameeksha
	 * Date:-16/02/2021
	 * Method:- ValidateUser
	 * description:- The validateUser method is used to validate username and password.
	 *
	 */
	@Override
	public String validateUser(String username, String password) 
	{
		return dao.validateUser(username, password);
	}
	
}
