package com.cg.sprint.vms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sadee
 * Date - 2021/02/16
 * Description - Result as entity class. result_id, result_election_id, candidate_name, total_votes are the 
 * 					attributes here. This contains getter and setter methods of all the attributes.
 * 					Also a default and parameterized constructor.
 */

@Entity
@Table(name="result_details")
public class Result 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="result_id")
	private Integer resultId;
	
	@Column(name="result_election_id")
	private Integer resultElectionId;
	
	@Column(name="candidate_name")
	private String candidateName;
	
	@Column(name="total_votes")
	private Integer totalVotes;

	public Result()
	{}
	
	
	public Result(Integer resultId, Integer resultElectionId, String candidateName, Integer totalVotes) {
		super();
		this.resultId = resultId;
		this.resultElectionId = resultElectionId;
		this.candidateName = candidateName;
		this.totalVotes = totalVotes;
	}

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getResultElectionId() {
		return resultElectionId;
	}

	public void setResultElectionId(Integer resultElectionId) {
		this.resultElectionId = resultElectionId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}
	
	
}
