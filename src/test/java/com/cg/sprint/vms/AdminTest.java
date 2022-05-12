package com.cg.sprint.vms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.sprint.vms.dao.AdminLoginDao;
import com.cg.sprint.vms.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest 
{

	@MockBean
	AdminLoginDao adao;
	
	@Autowired
	AdminService aservice;
	
	
    @Test
	public void TestvalidateUser()
	 {
             when(adao.validateUser("admin","1234")).thenReturn("valid user");
			assertEquals("valid user",aservice.validateUser("admin","1234"));
		}

}

