package com.cg.sprint.vms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sprint.vms.dao.CandidateDao;
import com.cg.sprint.vms.dao.ResultDao;
import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.entity.Result;

/**
 * @author Sadee
 * Date - 2021/02/16
 * Description - ResultserviceImpl is a service layer class which implements ResultService interface.
 *				 In this class we are defining that method to perform operation on result entity.
 */

@Service
@Transactional
public class ResultServiceImpl implements ResultService
{
	@Autowired
	CandidateDao cdao;
	
	@Autowired
	ResultDao rsdao;
	
	/** @author Sadee
	 * Date - 2021/02/16
	 * Method - getResult
	 * Description - The getResult method is implemented here to show us the list of Candidate and winner. 
	 */
	
	@Override
	public List<Result> getResult()
	{
		List<Candidate> lc=cdao.makeResult();
		List<Result> rslist=new ArrayList<Result>();
		for(Candidate cd:lc)
		{
			Result rs=new Result();
			rs.setTotalVotes(cd.getCandidateVotecount());
			rs.setCandidateName(cd.getCandidateName());
			rs.setResultElectionId(cd.getElection().getElectionId());
			rs=rsdao.save(rs);
			rslist.add(rs);
		}
		return rslist;
	}
	
	
}
