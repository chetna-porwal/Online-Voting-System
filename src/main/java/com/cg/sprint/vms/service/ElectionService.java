package com.cg.sprint.vms.service;

import java.time.LocalDate;
import java.util.List;
import com.cg.sprint.vms.entity.Election;
import com.cg.sprint.vms.exception.ElectionNotFoundException;

/**
 * @author cporwal
 *
 */
public interface ElectionService 
{
	Election createElection(Election el) throws ElectionNotFoundException;
	List<Election> getAllElections();
	Election deleteElection(Integer id );
	Election updateElection(Election el);
	List<Election> getElectionBetweenDates(LocalDate dat1,LocalDate dat2);
	List<Election> getAvailableElections();
	Election getElectionById(Integer id) throws ElectionNotFoundException;
}
