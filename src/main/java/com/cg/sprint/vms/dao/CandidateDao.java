package com.cg.sprint.vms.dao;

import java.util.List;
import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.exception.InvalidCandidateException;

public interface CandidateDao
{
	 Candidate updateCandidate(Candidate cad);
	 List<Candidate> getAllCandidates();
	 Candidate FindCandidate(Integer candidateId) throws InvalidCandidateException;
	 List<Candidate> FindByElection(Integer electionId);
	 Candidate DeleteCandidate(Integer candidateId) throws InvalidCandidateException;
	 public List<Candidate> makeResult();
	 Candidate addCandidate(Candidate cd);

}
