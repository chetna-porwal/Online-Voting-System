package com.cg.sprint.vms.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sprint.vms.dao.CandidateDao;
import com.cg.sprint.vms.dao.ElectionDao;
import com.cg.sprint.vms.dao.VoterDao;
import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.entity.Election;
import com.cg.sprint.vms.entity.Voter;
import com.cg.sprint.vms.exception.InvalidCandidateException;
import com.cg.sprint.vms.exception.InvalidVoterException;
import com.cg.sprint.vms.exception.RepeatedVotingException;


/**
 * @author miral
 *Date- 2021/16/02
 * description - VoterServiceImpl is a service layer class which implements VoterService interface. 
 * 				 In this class we are defining different methods to perform operation on Voter entity. 
 */

@Service
@Transactional
public class VoterServiceImpl implements VoterService
{
	static Integer count=0;
	
	@Autowired
	VoterDao dao;
	
	@Autowired
	CandidateDao daocd;
	
	@Autowired
	ElectionDao daoel;
	
		
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- addVoter
	 * parameter - Voter
	 * description - The addVoter method is implemented on Voter v object in database adding values in voter_details table. It throws custom 
	 * 					exception of type InvalidVoterException.
	 */
	@Override
	public String addVoter(Voter v) throws InvalidVoterException
	{
		LocalDate from = v.getVoterDob();
        LocalDate to = LocalDate.now();
        Period period = Period.between(from, to);

        if(period.getYears() >=18)
        {
        	v= dao.save(v);
        	return "voter added successfully";
        }
        else
        {
        	throw new InvalidVoterException("voter's age should be equal or greater than 18");
        }
        
	}
	
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- getVoters
	 * parameter - none
	 * description - The getVoter method is implemented to get all the voter instances that are stored in database.
	 */
	
	@Override
	public List<Voter> getVoters() 
	{
		List<Voter> list=dao.findAll();
		return list;
	}

	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- deleteVoter
	 * parameter - Integer id
	 * description - The deleteVoter method is implemented to delete voter instance from voter_details table using integer id parameter. It throws custom 
	 * 					exception of type InvalidVoterException.
	 */
	@Override
	public Voter deleteVoter(Integer id) throws InvalidVoterException 
	{
		try {
		Voter v=dao.findById(id).get();
		if(v==null)
			return null;
		dao.delete(v);
		return v;
		}
		catch (Exception e) 
		{
			throw new InvalidVoterException("voter not found");
		}
	}

	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- updateVoter
	 * parameter - Voter
	 * description - The updateVoter method is implemented on Voter v object in database to update values in voter_details table. 
	 */
	@Override
	public Voter updateVoter(Voter v) 
	{
		
		dao.save(v);
		return v;
	}
	
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- findVoterById
	 * parameter - Integer id
	 * description - The findVoterById method is implemented to get the details by passing the id parameter from voter_details table. 
	 */
	@Override
	public Voter findVoterById(Integer id) 
	{
		Voter v=dao.findById(id).get();
		return v;
	}
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- validateUser
	 * parameter - username, password
	 * description - validating the voter login. 
	 */
	
	@Override
	public String validateUser(String username,String password) 
	{
		Voter v=dao.getVoter(username);
		//System.out.println(v);
		if(v!=null)
		{
			String u=v.getVoterUsername();
			String p=v.getVoterPassword();
			System.out.println(u.equals(username) && p.equals(password));
			if(u.equals(username) && p.equals(password))
			{
				return "valid user";
			}
		return "username/password is invalid";
		}
		else
		{
			return "username/password is null";
		}
	}
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- insertVoterAsCandidate
	 * parameter - voterid, electionid
	 * description - this method is used to insert the voter as candidate in candidate table.
	 */
	@Override
	public Candidate insertVoterAsCandidate(Integer voterid,Integer electionid) 
	{
		Voter v=dao.findById(voterid).get();
		Candidate cd=new Candidate();
		cd.setCandidateAddress(v.getVoterAddress());
		cd.setCandidateName(v.getVoterName());
		cd.setCandidateDob(v.getVoterDob());
		cd.setCandidateContact(v.getVoterContact());
		Election e=new Election();
		e=daoel.findById(electionid).get();
		if(e!=null)
		{
			cd.setElection(e);
		}
		
		Candidate cd1=daocd.addCandidate(cd);
		return cd1;
	}
	/**
	 * @author miral
	 *Date- 2021/16/02
	 * METHOD- Vote
	 * parameter - vid, cid
	 * description -  this method is used to get the voter status if the voting is done or not.
	 */
	@Override
	public String Vote(Integer vid,Integer cid) throws RepeatedVotingException,InvalidVoterException, InvalidCandidateException
	{
		try {
			Voter v=dao.findById(vid).get();
			if(v!=null && v.getVoterStatus()!=true)
			{
				
				Candidate cd=daocd.FindCandidate(cid);
				count=count+1;
				cd.setCandidateVotecount(count);
				v.setVoterStatus(true);
				return "voted successfully";
			}
				else
				{
					throw new RepeatedVotingException("already voting done");
				}
				
			}
		
		catch(RepeatedVotingException e)
		{
			throw new RepeatedVotingException("already voting done");
		}
		catch(NoSuchElementException e)
		{
			throw new InvalidVoterException("voter not found");
		}
	}
}
	
