package com.cg.sprint.vms;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.sprint.vms.dao.VoterDao;
import com.cg.sprint.vms.entity.Voter;
import com.cg.sprint.vms.exception.InvalidVoterException;
import com.cg.sprint.vms.service.VoterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoterTest {
 
 @Autowired
 VoterService vs;
 
 @MockBean
 VoterDao vd;
 
 @Test
 public void getVotersTest() 
 {
  when(vd.findAll()).thenReturn(Stream.of(new Voter(123,"KrishG","mumbai",LocalDate.parse("2000-02-02"),"987654321","sad@12","pswrd",true),
    new Voter(124,"kash","Banglore",LocalDate.parse("2000-11-11"),"9876567893","user@banglore","pwd@banglore",true)).collect(Collectors.toList()));
  assertEquals(2, vs.getVoters().size());
 }
 @Test
 public void updateVoterTest() 
 {
	
  Voter v= new Voter(456,"zxcdfg","pune",LocalDate.parse("2000-11-11"),"987654321","asd12","password",true);
  when(vd.save(v)).thenReturn(v);
  assertEquals(v, vs.updateVoter(v));
 }
 	@Test 
	  public void addVoter() throws InvalidVoterException
	  {	  
	  Voter v =new Voter(234,"VoterFirst","navi mumbai",LocalDate.parse("2000-02-02"),"945245212","qw@234","pass123",false);
	  when(vd.save(v)).thenReturn(v);
	  assertEquals("voter added successfully",vs.addVoter(v)); 
	  }
	  
 
 }