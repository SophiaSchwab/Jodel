/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package com.trackplus.jodel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.trackplus.jodel.dao.DaoManager;

public class PrepareTests {

	public static void initDatabase() {
		DaoManager dm = DaoManager.getInstance();
		EntityManager em = dm.getEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et = em.getTransaction();
		et.begin();
		try {
			em.createNativeQuery("DELETE FROM COMMENT").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			em.createNativeQuery("DELETE FROM POST").executeUpdate();
		} catch (Exception e) {

		}

		try {
			em.createNativeQuery("DELETE FROM TUSER").executeUpdate();
		} catch (Exception e) {

		}
		et.commit();
		
		et = em.getTransaction();
		et.begin();
//		em.createNativeQuery("INSERT INTO TUSER (ID, NAME, EMAIL) VALUES (1, 'User1', 'email1@email.com')").executeUpdate();
//		em.createNativeQuery("INSERT INTO POST (ID, AUTHORID, TEXT, LONGITUDE, LATITUDE) VALUES (1, 1, 'Posted text', 48.0, 65.0)").executeUpdate();
//		em.createNativeQuery("INSERT INTO COMMENT (ID, AUTHORID, POSTID, TEXT, LONGITUDE, LATITUDE) VALUES (1, 1, 1, 'Comment text 1', 48.0, 65.0)").executeUpdate();
//		em.createNativeQuery("INSERT INTO COMMENT (ID, AUTHORID, POSTID, TEXT, LONGITUDE, LATITUDE) VALUES (2, 1, 1, 'Comment text 2', 48.0, 65.0)").executeUpdate();
		et.commit();
	}

}
