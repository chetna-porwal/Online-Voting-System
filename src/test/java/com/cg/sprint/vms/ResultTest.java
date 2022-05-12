package com.cg.sprint.vms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.sprint.vms.dao.ResultDao;
import com.cg.sprint.vms.entity.Result;
import com.cg.sprint.vms.service.ResultServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultTest {

	@Autowired
	private ResultServiceImpl rs;

	@MockBean
	private ResultDao rsdao;


	@Test
	public void getResultTest() {
		when(rs.getResult()).thenReturn(Stream.of(new Result(101,67,"miral",543)).collect(Collectors.toList()));
		assertEquals(1, rs.getResult().size());
	}

}


