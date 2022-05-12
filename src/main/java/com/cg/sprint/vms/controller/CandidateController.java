package com.cg.sprint.vms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.exception.InvalidCandidateException;
import com.cg.sprint.vms.service.CandidateService;

/**
 * @author Abhishek
 * Date:- 16/02/2021
 * Description:- RestController class to which is mapping the request url /candidate. Here we have defined
 * 				methods to perform operation on Candidate Entity
 */

@RestController
@RequestMapping("/candidate")
public class CandidateController 
{
	@Autowired
	CandidateService service;
	
	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Method:- updateCandidate
	 * parameter:- @RequestBody Candidate cad
	 * return type:- Candidate cad
	 * description:- @RequestMapping annotation is used to map the request with GET,POST and DELETE
	 */
	
	@RequestMapping(value="/updateCandidate", method=RequestMethod.POST)
	public Candidate updateCandidate(@RequestBody Candidate cad) 
	{
		service.updateCandidate(cad);
		return cad;
	}
	
	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Method:- getAllCandidates
	 * Parameter:- 
	 * Return type:- List<Candidate>
	 * Description:- @RequestMapping annotation is used to map the request with GET
	 */
	
	@RequestMapping(value="/getAllCandidates", method=RequestMethod.GET)
	public List<Candidate> getAllCandidates()
	{
		return service.getAllCandidates();
	}
	
	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- 
	 * Method:- FindCandidate
	 * parameter:- @PathVariable Integer candidateId 
	 * return type:- Candidate
	 * description:- @RequestMapping annotation is used to map the request with GET
	 */
	
	@RequestMapping(value="/findCandidateById/{id}", method=RequestMethod.GET)
	public Candidate FindCandidate(@PathVariable("id") Integer candidateId) throws InvalidCandidateException 
	{
		try {
			return service.FindCandidate(candidateId);
		}
		catch (Exception e) 
		{
			throw new InvalidCandidateException("candidate not found");
		}
	}
	
	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- 
	 * Method:- deleteCandidate
	 * parameter:- @PathVariable Integer id
	 * return type:- String
	 * description:- @RequestMapping annotation is used to map the request with DELETE
	 */
	
	@RequestMapping(value="/deleteCandidate/{id}",method=RequestMethod.DELETE)
	public String deleteCandidate(@PathVariable("id") Integer id ) throws InvalidCandidateException 
	{
		try
			{
				Candidate cd=service.DeleteCandidate(id);
				return cd.toString();
			}
			catch(Exception e)
			{
				throw new InvalidCandidateException("No candidate found ");
			}
		
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- 
	 * Method:- FindByElection
	 * parameter:- @PathVariable Integer electionId
	 * return type:- List<Candidate>
	 * description:- @RequestMapping annotation is used to map the request with GET,POST and DELETE
	 */
	
	@RequestMapping(value="/findCandidateByElectionId/{electionid}", method=RequestMethod.GET)
	public List<Candidate> FindByElection(@PathVariable("electionid") Integer electionId) 
	{
		return service.FindByElection(electionId);
	}
	
}
