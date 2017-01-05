package com.trackplus.jodel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.trackplus.jodel.model.Rating;


public class RatingDao {
	
	
	private static EntityManager em;
	private static RatingDao singleton;

	private RatingDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static RatingDao getInstance() {
		if (singleton == null) {
			singleton = new RatingDao();
		}
		return singleton;
	}

	public Rating getRating(Integer id) {
		return em.find(Rating.class, id);
	}

	public List<Rating> getRatings() {
		Query q = em.createQuery("select c from Rating c");
		List<Rating> ratings = q.getResultList();
		return ratings;
	}

	public void saveRating(Rating rating) {
		em.getTransaction().begin();
		em.persist(rating);
		em.getTransaction().commit();
	}

	public void deleteRating(Integer id) { //static added quick fix

		Rating cm = em.find(Rating.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
