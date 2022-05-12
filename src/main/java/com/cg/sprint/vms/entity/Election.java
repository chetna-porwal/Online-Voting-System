package com.cg.sprint.vms.entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author cporwal
 * Date:- 16/02/2021
 * Description:- Election is a Entity class, having attributes electionId, electionDate, electionStartTime
 *  electionEndTime. As well as the Election has getter and setter method along with parameterized constructor.
 */

@Entity
@Table(name="election_details")
public class Election
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="election_id")
	private Integer electionId;
	
	@Column(name = "election_date")
	private LocalDate electionDate;
		
	@JsonFormat(pattern="HH:mm:ss")
    @Column(name = "election_start_time",columnDefinition = "TIME")
	private LocalTime electionStartTime;
	
    @JsonFormat(pattern="HH:mm:ss")
    @Column(name = "election_end_time",columnDefinition = "TIME")
	private LocalTime electionEndTime;
	    
    @OneToMany(mappedBy = "election", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Candidate> candidates;

		public Election() {}

		
		public Election(Integer electionId, LocalDate electionDate, LocalTime electionStartTime,LocalTime electionEndTime) 
		{
			super();
			this.electionId = electionId;
			this.electionDate = electionDate;
			this.electionStartTime = electionStartTime;
			this.electionEndTime = electionEndTime;
		}


		public Integer getElectionId() {
			return electionId;
		}

		public void setElectionId(Integer electionId) {
			this.electionId = electionId;
		}

		
		public LocalDate getElectionDate() {
			return electionDate;
		}

		public void setElectionDate(LocalDate electionDate) {
			this.electionDate = electionDate;
		}

		public LocalTime getElectionStartTime() {
			return electionStartTime;
		}

		public void setElectionStartTime(LocalTime electionStartTime) {
			this.electionStartTime = electionStartTime;
		}

		public LocalTime getElectionEndTime() {
			return electionEndTime;
		}

		public void setElectionEndTime(LocalTime electionEndTime) {
			this.electionEndTime = electionEndTime;
		}

		@Override
		public String toString() {
			return "Election [electionId=" + electionId + ", electionDate=" + electionDate + ", electionStartTime="
					+ electionStartTime + ", electionEndTime=" + electionEndTime + ", candidates=" + candidates + "]";
		}

		
			
}
