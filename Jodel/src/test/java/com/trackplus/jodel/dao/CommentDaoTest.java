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
import com.trackplus.jodel.model.Comment;
import com.trackplus.jodel.model.Post;
import com.trackplus.jodel.model.Tuser;

public class CommentDaoTest {

	private static Tuser user = null;
	private static Post post = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		user = new Tuser();
		user.setName("George Test");
		user.setEmail("georg.test@test.com");
		UserDao.getInstance().saveTuser(user);
		post = new Post(); // Post Post abgeändert, sodass man das private static post von oben nimmt.
		post.setText("A post");
		post.setTuser(user);
		PostDao.getInstance().savePost(post) ;
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
		CommentDao c = CommentDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetComment() {
		CommentDao c = CommentDao.getInstance();
		Comment com = new Comment();
		com.setText("A comment");
		com.setTuser(user);
		com.setPost(post); //neu eingefügt
		c.saveComment(com);
		Comment coms = c.getComment(com.getId());
		assertNotNull(coms);
	}

	@Test
	public void testGetComments() {
		CommentDao c = CommentDao.getInstance();
		Comment com;
		for (long i=0; i < 10; ++i) {
			com = new Comment();
			com.setText("A comment "+i);
			com.setTuser(user);
			com.setPost(post);
			c.saveComment(com);
		}
		List<Comment> comments = c.getComments();
		assertTrue(comments.size()>=10);
	}

	@Test
	public void testSaveComment() {
		CommentDao c = CommentDao.getInstance();
		Comment com = new Comment();
		com.setText("A comment");
		com.setTuser(user);
		com.setPost(post);
		CommentDao.getInstance().saveComment(com);
		Comment coms = c.getComment(com.getId());
		assertNotNull(coms);
	}

	@Test
	public void testDeleteComment() {
		CommentDao c = CommentDao.getInstance();
		List<Comment> comments = c.getComments();
		for (Comment com: comments) {
			c.deleteComment(com.getId());
		}
		comments = c.getComments();
		assertTrue(comments.size()<1);
	}

}
