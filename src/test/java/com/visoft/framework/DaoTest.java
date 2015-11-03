package com.visoft.framework;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.visoft.framework.entity.BaseUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/jpa-context.xml"})
public class DaoTest {
	
	@Resource
	SimpleJpaRepository<BaseUser, Long> userDao;
	
	@Test
	public void testDaoCorrect(){
		System.out.println("-----");
	   System.out.print(userDao.count());
	}
}
