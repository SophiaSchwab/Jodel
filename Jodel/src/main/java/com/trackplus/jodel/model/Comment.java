/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package com.trackplus.jodel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the `COMMENT` database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Float latitude;
	private Float longitude;
	private Date postedat;
	private String text;
	
	private Post post;
	
	private Tuser tuser;

	public Comment() {
		postedat = new Date();
	}


	@Id
	@TableGenerator( name = "commentSeq", table = "SEQUENCE", 
	                 pkColumnName = "SEQ_NAME", pkColumnValue = "TCOMMENT", 
	                 valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.TABLE, generator = "commentSeq" )
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}


	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostedat() {
		return this.postedat;
	}

	public void setPostedat(Date postedat) {
		this.postedat = postedat;
	}


	@Lob
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}


	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="POSTID")
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne
	@JoinColumn(name="AUTHORID")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}