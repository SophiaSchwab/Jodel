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

import com.trackplus.jodel.model.Tuser;

public class UserDao {

	private static EntityManager em;
	private static UserDao singleton;

	private UserDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static UserDao getInstance() {
		if (singleton == null) {
			singleton = new UserDao();
		}
		return singleton;
	}

	public Tuser getTuser(Integer id) {
		return em.find(Tuser.class, id);
	}

	public List<Tuser> getTusers() {
		Query q = em.createQuery("select c from Tuser c");
		List<Tuser> users = q.getResultList();
		return users;
	}

	public void saveTuser(Tuser user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public void deleteTuser(Integer id) {

		Tuser cm = em.find(Tuser.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
