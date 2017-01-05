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
import com.trackplus.jodel.model.Rating;
import com.trackplus.jodel.model.Tuser;
import com.trackplus.jodel.model.Comment;

public class RatingDaoTest {
	
	private static Tuser user = null;
	private static Comment comment = null;
	private static Post post = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		user = new Tuser();
		user.setName("Hans Test");
		user.setEmail("Hans.test@test.com");
		UserDao.getInstance().saveTuser(user);
		post = new Post(); // Post Post abgeändert, sodass man das private static post von oben nimmt.
		post.setText("A post");
		post.setTuser(user);
		PostDao.getInstance().savePost(post) ;
		comment = new Comment();
		comment.setText("A comment");
		comment.setTuser(user);
		comment.setPost(post); //neu eingefügt
		CommentDao.getInstance().saveComment(comment);
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
		RatingDao c = RatingDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetRating() {
		RatingDao c = RatingDao.getInstance();
		Rating rating = new Rating();
		rating.setComment(comment);
		rating.setTuser(user);
		rating.setRating(1);
		c.saveRating(rating);
		Rating ratings = c.getRating(rating.getId());
		assertNotNull(ratings);
	}



	@Test
	public void testSaveRating() {
		RatingDao ratingDao = RatingDao.getInstance();
		Rating rating = new Rating();
		rating.setRating(1);
		ratingDao.saveRating(rating) ;
		Rating ratings = ratingDao.getRating(rating.getId());
		assertNotNull(ratings);
	}

	@Test
	public void testDeleteRating() {
		RatingDao ratingDao = RatingDao.getInstance();
		List<Rating> ratings = ratingDao.getRatings();
		for (Rating rating: ratings) {
			ratingDao.deleteRating(rating.getId());
		}
		ratings = ratingDao.getRatings();
		assertTrue(ratings.size()<1);
	}

}





