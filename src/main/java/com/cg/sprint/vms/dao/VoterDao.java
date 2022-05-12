package com.cg.sprint.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.sprint.vms.entity.Voter;

/**
 * @author miral
 * Date- 2021/16/02
 * description - extending JpaRepository to perform CRUD Operations.
 */

public interface VoterDao extends JpaRepository<Voter, Integer> 
{
	
	@Query(value="select v from Voter v where v.voterUsername=:user")
	public Voter getVoter(@Param("user") String user);

}
