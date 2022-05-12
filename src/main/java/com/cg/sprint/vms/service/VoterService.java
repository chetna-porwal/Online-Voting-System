package com.cg.sprint.vms.service;

import java.util.List;

import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.entity.Voter;
import com.cg.sprint.vms.exception.InvalidCandidateException;
import com.cg.sprint.vms.exception.InvalidVoterException;
import com.cg.sprint.vms.exception.RepeatedVotingException;

public interface VoterService 
{
	String validateUser(String username,String password);
	
	 String addVoter(Voter v) throws InvalidVoterException;
	 List<Voter> getVoters();
	 Voter deleteVoter(Integer id) throws InvalidVoterException;
	 Voter updateVoter(Voter v);
	 Voter findVoterById(Integer id) throws InvalidCandidateException;
	 Candidate insertVoterAsCandidate(Integer voterid,Integer electionid);
	 String Vote(Integer vid,Integer cid) throws RepeatedVotingException,InvalidVoterException, InvalidCandidateException;
}
