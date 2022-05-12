package com.cg.sprint.vms.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.sprint.vms.entity.Election;

/**
 * @author cporwal
 * date : 16/02/2021
 * description : ElectionDao is an interface that extends JpaRepository and here we created customized query also
 *  using @query annotation.
 */
public interface ElectionDao extends JpaRepository<Election,Integer>
{
	
	@Query(value="select el from Election el where el.electionDate > :dat1 and el.electionDate<:dat2 ")
	public List<Election> getElectionBetweenDates(@Param("dat1") LocalDate dat1,@Param("dat2") LocalDate dat2);

	@Query(value="select el from Election el where el.electionDate =CURDATE()")
	public List<Election> getAvailableElections();
	
}
