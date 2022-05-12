package com.cg.sprint.vms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.sprint.vms.dao.CandidateDao;
import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.exception.InvalidCandidateException;

/**
 * @author Abhishek
 * Date:- 16/02/2021
 * Description:- CandidateServiceImpl class which implements CandidateService interface to handle the servicebased operations 
 */
@Service
@Transactional 
public class CandidateServiceImpl implements CandidateService{
	
	@Autowired 
	CandidateDao dao;

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- UpdateCandidate Method is used to update the details of candidate
	 * Method:- updateCandidate()
	 * parameter:- Candidate
	 * return type:- Candidate
	 */
	@Override
	public Candidate updateCandidate(Candidate cad) {
		
		return dao.updateCandidate(cad);
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- getAllCandidates method is used to get all the candidate present in database
	 * Method:- getAllCandidates()
	 * parameter:- 
	 * return type:- List<Candidate> 
	 */
	@Override
	public List<Candidate> getAllCandidates() {
		
		return dao.getAllCandidates();
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- FindCandidate method is used to retrieve a perticular candidate using candidateId
	 * Method:- FindCandidate()
	 * parameter:- Integer candidateId
	 * return type:- Candidate
	 */
	@Override
	public Candidate FindCandidate(Integer candidateId) throws InvalidCandidateException
	{
		
		try {
			return dao.FindCandidate(candidateId);
		} catch (InvalidCandidateException e) 
		{
			throw new InvalidCandidateException("candidate not found");
		}
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- FindByElection is used to retrieve a list of candidates from that perticular election using election id
	 * Method:- FindByElection()
	 * parameter:- Integer electionId
	 * return type:- List<Candidate>
	 */
	@Override
	public List<Candidate> FindByElection(Integer electionId) 
	{
		
		return dao.FindByElection(electionId);
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- DeleteCandidate is used to delete a candidate from database
	 * Method:- DeleteCandidate()
	 * parameter:- Integer candidateId
	 * return type:- candidate
	 */
	@Override
	public Candidate DeleteCandidate(Integer candidateId) throws InvalidCandidateException 
	{
		try {
			Candidate cad=dao.DeleteCandidate(candidateId);
			return cad;
		}
		catch (Exception e) 
		{
			throw new InvalidCandidateException("candidate not found");
		}
	}
	
	 
}
