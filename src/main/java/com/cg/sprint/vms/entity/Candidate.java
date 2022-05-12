package com.cg.sprint.vms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Abhishek
 * Date:- 16/02/2021
 * Description:- Candidate is a Entity class, having attributes candidateId, candidateName, candidateAddress
 * 				candidate_contact, candidateDob, candidateVotecount, election. As well as the candidate has
 * 				getter and setter method along with parameterized constructor.
 */

@Entity
@Table(name="candidate_details")
public class Candidate 
{
	
	@Id
	@Column(name="candidate_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer candidateId;
	
	@Size(min=3,max=15,message="name must be atleast 3 characters long")
	@Column(name="candidate_name")
	private String candidateName;
	
	@Column(name="candidate_address")
	private String candidateAddress;
	
	@Pattern(regexp="[0-9]{10}")
	@Column(name="candidate_contact")
	private String candidateContact;
	
	@Column(name="candidate_dob")
	private LocalDate candidateDob;
	
	@Column(name="candidate_vote_count")
	private Integer candidateVotecount;
	
	//@Column(name="candidate_election_id")
	//private Integer election_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

	public Candidate()
	{}
	
	public Candidate(int i, String string, String string2, LocalDate parse, String string3, int j, int k) {
		// TODO Auto-generated constructor stub
	}
	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateAddress() {
		return candidateAddress;
	}

	public void setCandidateAddress(String candidateAddress) {
		this.candidateAddress = candidateAddress;
	}

	public String getCandidateContact() {
		return candidateContact;
	}

	public void setCandidateContact(String candidateContact) {
		this.candidateContact = candidateContact;
	}

	public LocalDate getCandidateDob() {
		return candidateDob;
	}

	public void setCandidateDob(LocalDate candidateDob) {
		this.candidateDob = candidateDob;
	}

	public Integer getCandidateVotecount() {
		return candidateVotecount;
	}

	public void setCandidateVotecount(Integer candidateVotecount) {
		this.candidateVotecount = candidateVotecount;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", candidateName=" + candidateName + ", candidateAddress="
				+ candidateAddress + ", candidateContact=" + candidateContact + ", candidateDob=" + candidateDob
				+ ", candidateVotecount=" + candidateVotecount +", election="
				+ election + "]";
	}
	
	

	
}