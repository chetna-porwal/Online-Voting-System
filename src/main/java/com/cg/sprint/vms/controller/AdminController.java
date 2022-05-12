package com.cg.sprint.vms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint.vms.entity.Result;
import com.cg.sprint.vms.service.AdminService;
import com.cg.sprint.vms.service.ResultService;

/**
 * 
 * @author Sameeksha,Sadee
 * Date:- 16/02/2021
 * Description:- Controller Class to handle the requests.
 */

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	@Autowired
	ResultService rs;
	
	/**
	 * @author Sameeksha
	 * Date:- 16/02/2021
	 * Method:- validateAdmin
	 * parameter :- Request Mapping is used to send the data using post request method.
	 */
	
	@RequestMapping(value="/validateAdmin", method=RequestMethod.POST)
	public String validateAdmin(@RequestParam("user_name") String username, @RequestParam("user_password") String user_pass)
	{
		String msg=adminService.validateUser(username,user_pass);
		return msg;
	}
	
	/**
	 * @author Sadee
	 * Date:- 16/02/2021
	 * Method:- publishResult()
	 * parameter :- none
	 * return type:- List<Result>
	 * description:- Request Mapping is used to send the data using post request method.
	 */
	
	@RequestMapping(value="/publishElectionResult", method=RequestMethod.GET)
	public List<Result> publishResult()
	{
		
		return rs.getResult();
	}
}