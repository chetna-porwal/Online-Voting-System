package com.cg.sprint.vms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author miral
 * Date- 2021/16/02
 * description - Voter as Entity class. voterId, voterName, voterAddress, voterDob, VoterContact are the attributes of this entity. 
 * 				It contains default constructor, setter and getter methods.
 *
 */

@Entity
@Table(name="voter_details")
public class Voter 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="voter_id")
	private Integer voterId;
	
	@Size(min=3,max=15,message="name must be atleast 3 characters long")
	@Column(name="voter_name")
	private String voterName;
	
	@Column(name="voter_address")
	private String voterAddress;
	
	@Column(name="voter_dob")
	@NotNull
	private LocalDate voterDob;
	
	@Pattern(regexp="[0-9]{10}")
	@Column(name="voter_contact")
	private String voterContact;
	
	@Pattern(regexp="[[a-z][A-Z][0-9]]{4,10}$",message="username must be alphanumeric with no spaces")
	@Column(name="voter_username",unique = true)
	@NotNull
	private String voterUsername;
	
	@Size(min=6,max=20,message="pasdsword must be atleast 6 characters long")
	@Column(name="voter_password",unique = true)
	@NotNull
	private String voterPassword;
	
	@Column(name="voter_status",columnDefinition = "boolean default false", nullable = false)
	private Boolean voterStatus=false;
	
	public Voter()
	{}
	
	
	public Voter(Integer voterId,
			@Size(min = 3, max = 15, message = "name must be atleast 3 characters long") String voterName,
			String voterAddress, @NotNull LocalDate voterDob, @Pattern(regexp = "[0-9]{10}") String voterContact,
			@Pattern(regexp = "[[a-z][A-Z][0-9]]{4,10}$", message = "username must be alphanumeric with no spaces") @NotNull String voterUsername,
			@Size(min = 6, max = 20, message = "pasdsword must be atleast 6 characters long") @NotNull String voterPassword,
			Boolean voterStatus) {
		super();
		this.voterId = voterId;
		this.voterName = voterName;
		this.voterAddress = voterAddress;
		this.voterDob = voterDob;
		this.voterContact = voterContact;
		this.voterUsername = voterUsername;
		this.voterPassword = voterPassword;
		this.voterStatus = voterStatus;
	}


	public Integer getVoterId() {
		return voterId;
	}

	public void setVoterId(Integer voterId) {
		this.voterId = voterId;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getVoterAddress() {
		return voterAddress;
	}

	public void setVoterAddress(String voterAddress) {
		this.voterAddress = voterAddress;
	}

	public LocalDate getVoterDob() {
		return voterDob;
	}

	public void setVoterDob(LocalDate voterDob) {
		this.voterDob = voterDob;
	}

	public String getVoterContact() {
		return voterContact;
	}

	public void setVoterContact(String voterContact) {
		this.voterContact = voterContact;
	}

	public String getVoterUsername() {
		return voterUsername;
	}

	public void setVoterUsername(String voterUsername) {
		this.voterUsername = voterUsername;
	}

	public String getVoterPassword() {
		return voterPassword;
	}

	public void setVoterPassword(String voterPassword) {
		this.voterPassword = voterPassword;
	}

	public Boolean getVoterStatus() {
		return voterStatus;
	}

	public void setVoterStatus(Boolean voterStatus) {
		this.voterStatus = voterStatus;
	}

	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", voterName=" + voterName + ", voterAddress=" + voterAddress
				+ ", voterDob=" + voterDob + ", voterContact=" + voterContact + ", voterUsername=" + voterUsername
				+ ", voterPassword=" + voterPassword + ", voterStatus=" + voterStatus + "]";
	}

	
	
}
