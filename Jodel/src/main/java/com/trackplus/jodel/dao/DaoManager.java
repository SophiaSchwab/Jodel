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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoManager {
	private static DaoManager dm;
	private static EntityManager em;
	
	private static final String PERSISTENCE_UNIT_NAME = "jodel";
	private static EntityManagerFactory factory;

	private DaoManager() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    em = factory.createEntityManager();
	}

	public static DaoManager getInstance() {
		if (dm == null) {
			dm = new DaoManager();
		}
		return dm;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}

}
