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
import com.trackplus.jodel.model.Post;
import com.trackplus.jodel.model.Tuser;

public class PostDaoTest {

	private static Tuser user = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		user = new Tuser();
		user.setName("George Test");
		user.setEmail("georg.test@test.com");
		UserDao.getInstance().saveTuser(user);
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
		PostDao c = PostDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetPost() {
		PostDao c = PostDao.getInstance();
		Post post = new Post();
		post.setText("A post");
		post.setTuser(user);
		c.savePost(post);
		Post posts = c.getPost(post.getId());
		assertNotNull(posts);
	}

	@Test
	public void testGetPosts() {
		PostDao p = PostDao.getInstance();
		Post post;
		for (long i=0; i < 10; ++i) {
			post = new Post();
			post.setText("A post No. " +i);
			post.setTuser(user);
			p.savePost(post) ;
		}
		List<Post> posts = p.getPosts();		
		assertTrue(posts.size()>=10);
	}

	@Test
	public void testSavePost() {
		PostDao postDao = PostDao.getInstance();
		Post post = new Post();
		post.setText("A post");
		post.setTuser(user);
		postDao.savePost(post) ;
		Post posts = postDao.getPost(post.getId());
		assertNotNull(posts);
	}

	@Test
	public void testDeletePosts() {
		PostDao postDao = PostDao.getInstance();
		List<Post> posts = postDao.getPosts();
		for (Post post: posts) {
			postDao.deletePost(post.getId());
		}
		posts = postDao.getPosts();
		assertTrue(posts.size()<1);
	}

}
