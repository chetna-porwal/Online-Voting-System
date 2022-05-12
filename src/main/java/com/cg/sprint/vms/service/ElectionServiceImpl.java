package com.cg.sprint.vms.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sprint.vms.dao.ElectionDao;
import com.cg.sprint.vms.entity.Election;
import com.cg.sprint.vms.exception.ElectionNotFoundException;

/**
 * @author cporwal
 * date : 16/02/2021
 * description : ElectionServiceImpl is a service layer class which implements ElectionService interface.Here we are
 *  defining methods to perform operation on Election entity.
 */

@Service
@Transactional
public class ElectionServiceImpl implements ElectionService
{
	@Autowired
	ElectionDao dao;
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : createElection(Election el)
	 * parameter : Election
	 * return type : Election
	 * description : The createElection(Election el) method is implemented to insert an election object in database
	 *  in election_details table which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@Override
	public Election createElection(Election el) throws ElectionNotFoundException 
	
	{
		if(el==null)
		{
			throw new ElectionNotFoundException("election not created");
		}
		return dao.save(el);
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getAllElections()
	 * parameter : none
	 * return type : List<Election>
	 * description : The getAllElections() method is implemented to get all election instances that are stored in database
	 *  from election_details table. 
	 */

	@Override
	public List<Election> getAllElections() 
	{
		return dao.findAll();
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : deleteElection(Integer id)
	 * parameter : Integer id
	 * return type : Election
	 * description : The deleteElection(Integer id) method is implemented to delete an election object
	 *  from election_details table. 
	 */
	@Override
	public Election deleteElection(Integer id)
	{
		Election el= dao.getOne(id);
		if(el==null)
		{
			return null;
		}
		dao.delete(el);
	return el;
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : updateElection(Election el)
	 * parameter : Election
	 * return type : Election
	 * description : The updateElection(Election el) method is implemented to update an existing election object
	 *  in election_details table. 
	 */
	@Override
	public Election updateElection(Election el) 
	{	
		return dao.save(el);

	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getElectionById(Integer id)
	 * parameter : Integer id
	 * return type : Election
	 * description : The getElectionById(Integer id) method is implemented to get an election object by using id from
	 *  election_details table in database which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@Override
	public Election getElectionById(Integer id) throws ElectionNotFoundException 
	{
			try 
			{
			Election el= dao.getOne(id);
			if(el==null)
				{
					throw new ElectionNotFoundException("election of this id is not present");
				}
				else
				{
					return el;
				}
			}
			catch(ElectionNotFoundException e)
			{
				throw new ElectionNotFoundException("no election found for this id");
			}
		
			
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getElectionBetweenDates(LocalDate dat1,LocalDate dat2)
	 * parameter : LocalDate dat1,LocalDate dat2
	 * return type : List<Election>
	 * description : The getElectionBetweenDates(LocalDate dat1,LocalDate dat2) method is implemented to find election instances
	 *  in election_details table. 
	 */

	@Override
	public List<Election> getElectionBetweenDates(LocalDate dat1,LocalDate dat2) 
	{
		return dao.getElectionBetweenDates(dat1, dat2);
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getAvailableElections()
	 * parameter : none
	 * return type : List<Election>
	 * description : The getAvailableElections() method is implemented to get all available election instances for current date
	 *  from election_details table. 
	 */

	@Override
	public List<Election> getAvailableElections() 
	{
		return dao.getAvailableElections();
	}
	
}
