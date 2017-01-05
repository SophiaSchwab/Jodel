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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.trackplus.jodel.model.Post;
import com.trackplus.jodel.model.Tuser;

public class PostDao {

	private static EntityManager em;
	private static PostDao singleton;

	private PostDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static PostDao getInstance() {
		if (singleton == null) {
			singleton = new PostDao();
		}
		return singleton;
	}

	public Post getPost(Integer id) {
		return em.find(Post.class, id);
	}

	public List<Post> getPosts() {
		Query q = em.createQuery("select c from Post c");
		List<Post> posts = q.getResultList();
		return posts;
	}

	public void savePost(Post post) {
		em.getTransaction().begin();
		Tuser u = UserDao.getInstance().getTuser(post.getTuser().getId());
//		if (u == null) {
//			u = post.getTuser();
//			u.setId(null);
//			post.setTuser(u);
//		}
		em.persist(post); //hier merge in persist geändert.
		em.getTransaction().commit();
	}

	public void deletePost(Integer id) {

		Post post = em.find(Post.class, id);
		if (post != null) {
			em.getTransaction().begin();
			
			em.remove(post);
			
			em.getTransaction().commit();
		}
	}
	public List<Post> getLocationPosts(float longi, float lati, int umkreis) {
		if (umkreis <= 5) { // checkt ob radius 5km wenn ja gibt post zurück
							// wenn nein nichts
			float radius = (float) umkreis; 
			
		
			Query q = em.createQuery(
					"select c from Post c where (c.Longitude BETWEEN " + (longi + radius) + " And " + (longi - radius)
							+ ") AND " + "( c.Latitude BETWEEN " + (lati + radius) + " And " + (lati - radius) + " )");
			;
			List<Post> posts = q.getResultList();

			return posts;
		} else {
			System.out.println("Wählen Sie einen passenden Umkreis als Zahl ein!");
			return null;
		}
	}
}