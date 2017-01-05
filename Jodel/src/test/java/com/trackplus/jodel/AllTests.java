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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.trackplus.jodel.dao.CommentDaoTest;
import com.trackplus.jodel.dao.DaoManagerTest;
import com.trackplus.jodel.dao.PostDaoTest;
import com.trackplus.jodel.dao.RatingDaoTest;
import com.trackplus.jodel.dao.UserDaoTest;

@RunWith(Suite.class)
@SuiteClasses({DaoManagerTest.class,
			   CommentDaoTest.class,
			   PostDaoTest.class,
			   UserDaoTest.class,
			   RatingDaoTest.class
			   })
public class AllTests {

}
