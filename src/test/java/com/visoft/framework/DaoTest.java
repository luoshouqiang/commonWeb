package com.visoft.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.visoft.framework.repository.BaseUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/jpa-context.xml"})
public class DaoTest {
	
	@Autowired
	BaseUserDao userDao;
	
	@Test
	public void testDaoCorrect(){
		System.out.println("-----");
	   System.out.print(userDao.count());
	}
}
