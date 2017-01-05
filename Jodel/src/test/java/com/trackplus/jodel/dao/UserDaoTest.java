/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package com.trackplus.jodel.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trackplus.jodel.PrepareTests;
import com.trackplus.jodel.model.Tuser;

public class UserDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		UserDao c = UserDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetTuser() {
		UserDao c = UserDao.getInstance();
		Tuser user = new Tuser();
		user.setName("John Doe");
		c.saveTuser(user);
		Tuser users = c.getTuser(user.getId());
		assertNotNull(users);
	}

	@Test
	public void testGetTusers() {
		UserDao c = UserDao.getInstance();
		Tuser user;
		for (long i=0; i < 10; ++i) {
			user = new Tuser();
			user.setName("John Doe sen. "+i);
			c.saveTuser(user) ;
		}
		List<Tuser> users = c.getTusers();
		assertTrue(users.size()>=10);
	}

	@Test
	public void testSaveTuser() {
		UserDao userDao = UserDao.getInstance();
		Tuser user = new Tuser();
		user.setName("Mary Save");
		userDao.saveTuser(user) ;
		Tuser users = userDao.getTuser(user.getId());
		assertNotNull(users);
	}

	@Test
	public void testDeleteTusers() {
		UserDao userDao = UserDao.getInstance();
		List<Tuser> users = userDao.getTusers();
		for (Tuser user: users) {
			userDao.deleteTuser(user.getId());
		}
		users = userDao.getTusers();
		assertTrue(users.size()<1);
	}

}
