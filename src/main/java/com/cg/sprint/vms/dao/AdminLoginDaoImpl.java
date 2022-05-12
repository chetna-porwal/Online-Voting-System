package com.cg.sprint.vms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.sprint.vms.entity.Admin;
import com.cg.sprint.vms.exception.InvalidAdminException;

/**
 * @author Sameeksha
 * Date:- 16/02/2021
 * Description:-AdminLoginDaoImpl class which implements AdminLoginDao to handle the operations on database.
 */
@Repository
public class AdminLoginDaoImpl implements AdminLoginDao 
{

	@PersistenceContext
	EntityManager em;
	
	/**
	  * @author Sameeksha
	  * Date:- 16/02/2021
	  * Description:- ValidateUser Method is used to validate the details of Admin.
	  * Method:- validateUser(String username, String password)
	  * parameter:- username,password
	  * return type:- String
	  */

	@Override
	public String validateUser(String username, String password)
	{
		String msg="";
		try 
		{
			Admin admin=em.find(Admin.class, username);
			if(admin==null)
			{
				throw new InvalidAdminException("Invalid Username/Password");
			}
			else if(!(admin.getPassword().equals(password))) 
			{ 
				throw new InvalidAdminException("Invalid password try again");
			}
			else 
			{ 
				return "valid user..!!!";
			}
			
		} catch (Exception e) 
		{
			return e.getMessage();
		}	
	}
	
}
