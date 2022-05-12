package com.cg.sprint.vms;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.sprint.vms.dao.CandidateDao;
import com.cg.sprint.vms.dao.ElectionDao;
import com.cg.sprint.vms.entity.Candidate;
import com.cg.sprint.vms.entity.Election;
import com.cg.sprint.vms.exception.ElectionNotFoundException;
import com.cg.sprint.vms.exception.InvalidCandidateException;
import com.cg.sprint.vms.service.CandidateService;
import com.cg.sprint.vms.service.ElectionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElectionTest 
{
	@Autowired
	ElectionService eservice;
	@Autowired
	CandidateService cservice;
	
	
	@MockBean
	ElectionDao edao;

	@MockBean
	CandidateDao cdao;

	
	@Test
	public void createElectionTest() throws ElectionNotFoundException
	{
		Election e1=new Election(1,LocalDate.parse("2020-12-29"),LocalTime.parse("08:30:00"),LocalTime.parse("06:30:00"));
		when(edao.save(e1)).thenReturn(e1);
		assertEquals(e1,eservice.createElection(e1));
	}
	
	@Test
	public void updateElectionTest()
	{
		Election e1=new Election(1,LocalDate.parse("2020-12-29"),LocalTime.parse("08:30:00"),LocalTime.parse("06:30:00"));
		when(edao.save(e1)).thenReturn(e1);
		assertEquals(e1,eservice.updateElection(e1));
	}
	
	@Test
	public void deleteElectionTest()
	{
		Election e1=edao.getOne(1);
		when(edao.getOne(1)).thenReturn(e1);
		Election e2=eservice.deleteElection(1);
		assertEquals(e1,e2);
		
	}
	
	@Test
	public void getElectionsTest()
	{
		when(edao.findAll()).thenReturn(Stream
				.of(new Election(1,LocalDate.parse("2020-12-29"),LocalTime.parse("08:30:00"),LocalTime.parse("06:30:00")),
						new Election(2,LocalDate.parse("2021-01-15"),LocalTime.parse("08:00:00"),LocalTime.parse("07:30:00")))
				.collect(Collectors.toList()));
		assertEquals(2,eservice.getAllElections().size());
	}
	
	@Test
	public void getElectionBetweenDatesTest()
	{
		LocalDate d1=LocalDate.parse("2020-12-20");
		LocalDate d2=LocalDate.parse("2021-01-20");
		when(edao.getElectionBetweenDates(d1, d2)).thenReturn(Stream
				.of(new Election(1,LocalDate.parse("2020-12-29"),LocalTime.parse("08:30:00"),LocalTime.parse("06:30:00")),
						new Election(2,LocalDate.parse("2021-01-15"),LocalTime.parse("08:00:00"),LocalTime.parse("07:30:00")))
				.collect(Collectors.toList()));
		assertEquals(2,eservice.getElectionBetweenDates(d1, d2).size());
	}
	@Test 
	public void getElectionByIdTest() throws ElectionNotFoundException 
	{ 
		Election el=new Election();
		el.setElectionId(1);
		el.setElectionDate(LocalDate.parse("2020-12-29"));
		el.setElectionStartTime(LocalTime.parse("08:30:00"));
		el.setElectionEndTime(LocalTime.parse("06:30:00"));
	 when(edao.getOne(1)).thenReturn(el);
	 	 
	 assertEquals(el,eservice.getElectionById(1));
	}
	
	@Test
	void testEmployeeNotException() {
	 
		when(edao.getOne(1)).thenReturn(null);
	  Assertions.assertThrows(ElectionNotFoundException.class, () -> {
	    eservice.getElectionById(1);
	  });
	 
	}
	
	@Test
	void testCreateEmployeeException() 
	{
		Election e=null;
		when(edao.save(e)).thenReturn(e);
	  Assertions.assertThrows(ElectionNotFoundException.class, () -> {
	    eservice.createElection(e);
	  });
	 
	}
		
	}
