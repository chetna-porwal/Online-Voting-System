package com.cg.sprint.vms;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.sprint.vms.dao.CandidateDao;
import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.entity.Election;
import com.cg.sprint.vms.exception.InvalidCandidateException;
import com.cg.sprint.vms.service.CandidateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateTest {
	
	@Autowired
	private CandidateService service;
	
	@MockBean
	private CandidateDao dao;
	
	@org.junit.jupiter.api.Test
	public void TestgetAllCandidates()
	{
		when(dao.getAllCandidates()).thenReturn(Stream
				.of(new Candidate(1,"Abad","9856214534",LocalDate.parse("1998-07-23"),"Abhishek",20,100),
						new Candidate(2,"qwe","9876543210",LocalDate.parse("2000-11-11"),"asd",19,100))
				.collect(Collectors.toList()));
		assertEquals(2,service.getAllCandidates().size());
	}
	
	@Test
	public void deleteCandidateTest() throws InvalidCandidateException
	{
		Candidate c1=dao.FindCandidate(1);
		
		when(dao.FindCandidate(1)).thenReturn(c1);
		Candidate c2=service.DeleteCandidate(1);
		assertEquals(c1,c2);
		
	}
	
	   @Test
	   public void TestfindCandidateByElectionId()
	   { 
	    Election e1=new Election();
	    e1.setElectionId(1111);
	    Candidate cad=new Candidate();
	    cad.setCandidateId(100);
	    cad.setCandidateAddress("Abad");
	    cad.setCandidateContact("1234567890");
	    cad.setCandidateDob(LocalDate.parse("1998-07-23"));
	    cad.setCandidateName("Abhishek");
	    cad.setCandidateVotecount(20);
	    cad.setElection(e1);
	    when(dao.FindByElection(1111)).thenReturn(Stream
	        .of(new Candidate(100,"Abad","1234567890",LocalDate.parse("1998-07-23"),"Abhishek",20,1111))
	        .collect(Collectors.toList()));
	    assertEquals(1,service.FindByElection(1111).size());
	   }
	   
	   @Test
	   public void TestFindCandidateByCandidateId() throws InvalidCandidateException {
		   Election e1=new Election();
		    e1.setElectionId(1111);
		    Candidate cad=new Candidate();
		    cad.setCandidateId(100);
		    cad.setCandidateAddress("Abad");
		    cad.setCandidateContact("1234567890");
		    cad.setCandidateDob(LocalDate.parse("1998-07-23"));
		    cad.setCandidateName("Abhishek");
		    cad.setCandidateVotecount(20);
		    cad.setElection(e1);
		    when(dao.FindCandidate(100)).thenReturn(cad);
		    assertEquals(cad,service.FindCandidate(100));
	   }
	   
	   @Test
	   public void TestUpdateCandidate()
	   {
		   Candidate cad1=new Candidate(100,"Abad","123456789",LocalDate.parse("1998-07-23"),"tanmay",18,1111);
		   when(dao.updateCandidate(cad1)).thenReturn(cad1);
		   assertEquals(cad1,service.updateCandidate(cad1));  
	   }
}