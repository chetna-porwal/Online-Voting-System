package com.cg.sprint.vms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.entity.Voter;
import com.cg.sprint.vms.exception.InvalidCandidateException;
import com.cg.sprint.vms.exception.InvalidVoterException;
import com.cg.sprint.vms.exception.RepeatedVotingException;
import com.cg.sprint.vms.service.VoterService;

/**
 * @author miral
 *Date- 2021/16/02
 * description - controller class to handle the requests and maps request url /voter. 
 */
@RestController
@RequestMapping("/voter")
public class VoterController 
{
	@Autowired
	VoterService service;

	
	@RequestMapping(value="/addVoterList",method=RequestMethod.POST)
	public void addVoterList(@RequestBody List<Voter> vlist) throws InvalidVoterException 
	{
		for(Voter v:vlist)
		{
			service.addVoter(v);
		}
	}
	

	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- add voter
	 * parameter - /addvoter
	 * description - adding the values in database. It throws custom 
	 * 					exception of type InvalidVoterException.
	 * @throws InvalidVoterException 
	 */
	@RequestMapping(value="/addVoter", method=RequestMethod.POST)
	public String addVoter(@RequestBody Voter v) throws InvalidVoterException
	{
		try {
		return service.addVoter(v);
		}
		catch(Exception e)
		{
			throw new InvalidVoterException("voter can not be added");
		}
	}
	
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- @RequestMapping
	 * parameter - /getAllVoters
	 * description - get all the voter attributes
	 */
	
	@RequestMapping(value="/getAllVoters", method=RequestMethod.GET)
	public List<Voter> getVoter()
	{
		return service.getVoters();
		
	}
	
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- @RequestMapping
	 * parameter - /deleteVoter
	 * description - delete any voter attribute .It throws custom 
	 * 					exception of type InvalidVoterException.
	 */
	
	@RequestMapping(value="/deleteVoter/{id}",method=RequestMethod.DELETE)
	public String deleteVoter(@PathVariable Integer id) throws InvalidVoterException
	{
		try {
		Voter v=service.deleteVoter(id);
			return v.toString();
		}
		catch(Exception e)
		{
			throw new InvalidVoterException("voter not found");
		}
				
	}
	
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- @RequestMapping
	 * parameter - /updateVoter
	 * description- updating any voter attribute in table
	 */
	
	@RequestMapping(value="/updateVoter", method=RequestMethod.POST)
	public Voter updateVoter(@RequestBody Voter v)
	{
		service.updateVoter(v);
		return v;
	}
	
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- @RequestMapping
	 * parameter - /getVoterById
	 * description - The getVoterById method is implemented to get the details by passing the id parameter from voter_details table. It throws custom 
	 * 					exception of type InvalidVoterException.
	 */
	@RequestMapping(value="/getVoterById/{id}", method=RequestMethod.GET)
	public Voter findVoter(@PathVariable("id") Integer id ) throws InvalidVoterException
	{
		try {
		
		return service.findVoterById(id);
		}
		catch (Exception e) 
		{
			throw new InvalidVoterException("voter not found");
		}
	}
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD-  @RequestMapping
	 * parameter - /insertVoterAsCad
	 * description - this method is used to insert the voter as candidate in candidate table.It throws custom 
	 * 					exception of type InvalidVoterException.
	 */
	
	@RequestMapping(value="/insertVoterAsCad/{voterid}/{electionid}", method=RequestMethod.POST)
	public Candidate insertVoter(@PathVariable("voterid") Integer vid,@PathVariable("electionid") Integer eid ) throws InvalidVoterException
	{
		try {
		return service.insertVoterAsCandidate(vid,eid);
		}
		catch(Exception e)
		{
			throw new InvalidVoterException("voter can not be added as candidate");
		}
	}
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD-  @RequestMapping
	 * parameter - /vote/
	 * description - this method is used to give the vote by voter and get the status.It throws custom 
	 * 					exception of type InvalidVoterException.
	 */
	@RequestMapping(value="/vote/{voterid}/{candidateid}", method=RequestMethod.POST)
	public String giveVote(@PathVariable("voterid") Integer vid,@PathVariable("candidateid") Integer cid) throws RepeatedVotingException,InvalidVoterException, InvalidCandidateException
	{
		return service.Vote(vid,cid);
		
	}
	
}

