package com.trackplus.jodel.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATING database table.
 * 
 */
@Entity
@NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "ratingSeq", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "TRATING", valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ratingSeq")
	private int id;

	private int rating;

	// bi-directional many-to-one association to Comment
	@ManyToOne
	@JoinColumn(name = "CommentID")
	private Comment comment;

	// bi-directional many-to-one association to Tuser
	@ManyToOne
	@JoinColumn(name = "UserID")
	private Tuser tuser;

	public Rating() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}
