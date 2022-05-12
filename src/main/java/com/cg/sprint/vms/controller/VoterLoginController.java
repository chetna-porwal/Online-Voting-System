package com.cg.sprint.vms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint.vms.service.VoterService;

/**
 * @author miral
 * Date- 2021/16/02
 * description - controller class to handle the requests 
 */

@RestController
@RequestMapping("/user")
public class VoterLoginController 
{
	@Autowired
	VoterService vservice;
	@RequestMapping(value="/validateuser", method=RequestMethod.POST)
	public String validateVoter(@RequestParam("voter_username") String username, @RequestParam("voter_password") String password)
	{
		String msg=vservice.validateUser(username,password);
		
		return msg;
		
	}
}


