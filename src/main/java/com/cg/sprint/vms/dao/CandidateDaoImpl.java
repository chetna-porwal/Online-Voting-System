package com.cg.sprint.vms.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.exception.InvalidCandidateException;

/**
 * @author Abhishek
 * Date:- 16/02/2021
 * Description:- CandidateDaoImpl class which implements CandidateDao to handle the operations on database
 */

@Repository
public class CandidateDaoImpl implements CandidateDao
{
	@PersistenceContext
	EntityManager em;
	
	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- UpdateCandidate Method is used to update the details of candidate
	 * Method:- updateCandidate()
	 * parameter:- Candidate
	 * return type:- Candidate
	 */
	
	@Override
	public Candidate updateCandidate(Candidate cad) 
	{
		em.merge(cad);
		return cad;
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
		TypedQuery<Candidate> qry=em.createQuery("select cad from Candidate cad", Candidate.class);
		List<Candidate> list=qry.getResultList();
	return list;
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
	public Candidate FindCandidate(Integer candidateId) throws InvalidCandidateException {
		Candidate cad=em.find(Candidate.class, candidateId);
		if(cad==null)
		{
			throw new InvalidCandidateException("candidate not found");
		}
		else
		{
			return cad;
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
		String str="SELECT c FROM Election e JOIN e.candidates c ON e.electionId=:id";
		
		TypedQuery<Candidate> qry=em.createQuery(str, Candidate.class);
		qry.setParameter("id",electionId);
		List<Candidate> list=qry.getResultList();
		return list;
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
		try 
		{
		Candidate cad=em.find(Candidate.class,candidateId);
		
		em.remove(cad);
		return cad;
		}
		catch (Exception e) 
		{
			throw new InvalidCandidateException("candidate not found");
		}
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- makeResult is used to have the result of the election
	 * Method:- makeResult()
	 * parameter:- 
	 * return type:- List<Candidate> 
	 */
	
	@Override
	public List<Candidate> makeResult() 
	{
		String str="SELECT c FROM Candidate c WHERE c.candidateVotecount =ANY(SELECT MAX(c.candidateVotecount) FROM Election e INNER JOIN e.candidates c GROUP BY e.electionId)";
				
		TypedQuery<Candidate> qry=em.createQuery(str, Candidate.class);
		List<Candidate> list=qry.getResultList();

		return list;
	}

	/**
	 * @author Abhishek
	 * Date:- 16/02/2021
	 * Description:- addCandidate is used to add candidate
	 * Method:- addCandidate()
	 * parameter:- Candidate 
	 * return type:- Candidate
	 */
	
	@Override
	public Candidate addCandidate(Candidate cd) 
	{
		em.persist(cd);
		return cd;
	}


		
}
