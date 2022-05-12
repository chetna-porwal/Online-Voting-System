package com.cg.sprint.vms.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint.vms.entity.Election;
import com.cg.sprint.vms.exception.ElectionNotFoundException;
import com.cg.sprint.vms.service.ElectionService;

/**
 * @author cporwal
 * date : 16/02/2021
 * description : ElectionController is a rest controller class which is mapping the request url /election.Here we are
 *  defining methods to perform operation on Election entity.
 */
@RestController
@RequestMapping("/election")
public class ElectionController
{
	
	@Autowired
	ElectionService service;
	
	
	@RequestMapping(value="/createElectionList",method=RequestMethod.POST)
	public void addElectionList(@RequestBody List<Election> elist) throws ElectionNotFoundException 
	{
		for(Election e:elist)
		{
			service.createElection(e);
		}
	}
	
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : addElection(Election el)
	 * parameter : Election
	 * return type : Election
	 * description : The addElection(Election el) method is implemented to map the request url /election/createElection
	 *  which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@RequestMapping(value="/createElection",method=RequestMethod.POST)
	public Election addElection(@RequestBody Election el) throws ElectionNotFoundException 
	{
		try {
		return service.createElection(el);
		}
		catch(Exception e)
		{
			throw new ElectionNotFoundException("election not added");
		}
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getAllElections()
	 * parameter : none
	 * return type : List<Election>
	 * description : The getAllElections() method is implemented to map the request url /election/getAllElections
	 *  which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@RequestMapping(value="/getAllElections",method=RequestMethod.GET)
	public List<Election> getAllElections()
	{
		List<Election> l=service.getAllElections();
		return l;
		
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : deleteElection(Integer id)
	 * parameter : Integer id
	 * return type : String
	 * description : The deleteElection(Integer id) method is implemented to map the request url /election//deleteElection/{id}
	 *  which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@RequestMapping(value="/deleteElection/{id}",method=RequestMethod.DELETE)
	public String deleteElection(@PathVariable("id") Integer id ) throws ElectionNotFoundException 
	{
			
				Election el=service.deleteElection(id);
				return el.toString();
			

	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : updateElection(Election el)
	 * parameter : Election
	 * return type : Election
	 * description : The updateElection(Election el) method is implemented to map the request url /election/updateElection
	 *  which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@RequestMapping(value="/updateElection",method=RequestMethod.POST)
	public Election updateElection(@RequestBody Election el ) throws ElectionNotFoundException 
	{
		try {
			
				service.updateElection(el);
				return el;
		}
		catch (Exception e) 
		{
			throw new ElectionNotFoundException("no election found for this id");
		}
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getAllElections()
	 * parameter : Integer id
	 * return type : String
	 * description : The getElectionbyId(Integer id) method is implemented to map the request url /election//getElectionById/{id}
	 *  which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@RequestMapping(value="/getElectionById/{id}",method=RequestMethod.GET)
	public String getElectionbyId(@PathVariable("id") Integer id ) throws ElectionNotFoundException
	{
		try 
		{
			Election el=service.getElectionById(id);
				return el.toString();
		}catch(Exception e)
		{
			throw new ElectionNotFoundException("election of this id not found");
			 
		}
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getAvailableElectionBetweenDates(String dat1,String dat2)
	 * parameter : String dat1,String dat2
	 * return type : List<Election>
	 * description : The getAvailableElectionBetweenDates(String dat1,String dat2) method is implemented to map the request 
	 * url /election/getElectionByDate which can throw a custom exception of type ElectionNotFoundException. 
	 */

	@RequestMapping(value="/getElectionByDate",method=RequestMethod.GET)
	public List<Election> getAvailableElectionBetweenDates(@Param("dat1") String dat1,@Param("dat2") String dat2) throws ElectionNotFoundException
	{
		try {
		LocalDate dt1=LocalDate.parse(dat1);
		LocalDate dt2=LocalDate.parse(dat2);
		List<Election> list=service.getElectionBetweenDates(dt1, dt2);
		if(list!=null)
		{
			return list;
		}
		else
		{
			return null;
		}
		}
		catch(Exception e)
		{
			throw new ElectionNotFoundException("no election found between given dates");
		}
	}
	
	/**
	 * @author cporwal
	 * date : 16/02/2021
	 * method : getAvailableElection()
	 * parameter : none
	 * return type : List<Election>
	 * description : The getAvailableElection() method is implemented to map the request url
	 *  /election/getAvailableElections. 
	 */

	@RequestMapping(value="/getAvailableElections",method=RequestMethod.GET)
	public List<Election> getAvailableElection()
	{
		List<Election> list=service.getAvailableElections();
		return list;
	}
}
