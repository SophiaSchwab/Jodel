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

import com.trackplus.jodel.model.Comment;

public class CommentDao {

	private static EntityManager em;
	private static CommentDao singleton;

	private CommentDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static CommentDao getInstance() {
		if (singleton == null) {
			singleton = new CommentDao();
		}
		return singleton;
	}

	public Comment getComment(Integer id) {
		return em.find(Comment.class, id);
	}

	public List<Comment> getComments() {
		Query q = em.createQuery("select c from Comment c");
		List<Comment> comments = q.getResultList();
		return comments;
	}

	public void saveComment(Comment comment) {
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
	}

	public void deleteComment(Integer id) {

		Comment cm = em.find(Comment.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
