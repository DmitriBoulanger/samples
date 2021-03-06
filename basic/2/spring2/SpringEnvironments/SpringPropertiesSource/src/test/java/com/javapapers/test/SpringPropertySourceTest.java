package com.javapapers.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.dbo.samples.spring.environments.config.ApplicationConfig;
import de.dbo.samples.spring.environments.model.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class)
public class SpringPropertySourceTest {

	private String DEV_REST_API_URL = "http://devapp.com/restapi/results";
	private String QA_REST_API_URL = "http://qaapp.com/restapi/results";
	
	@Autowired
	private Resource resource;

	@Test
	//@Ignore
	public void testDevResource() {
		System.out.println("RESTful API EndPoint URL :: " + resource.getUrl());
		assertNotNull(resource);
		assertTrue(DEV_REST_API_URL.equals(resource.getUrl()));
	}
	
	@Test
	@Ignore
	public void testQAResource() {
		System.out.println("RESTful API EndPoint URL :: " + resource.getUrl());
		assertNotNull(resource);
		assertTrue(QA_REST_API_URL.equals(resource.getUrl()));

	}
}
